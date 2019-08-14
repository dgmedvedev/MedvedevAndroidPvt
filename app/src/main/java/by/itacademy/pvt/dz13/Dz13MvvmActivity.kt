package by.itacademy.pvt.dz13

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.Poi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.IOException

class Dz13MvvmActivity : FragmentActivity(), OnMapReadyCallback {

    private val lazyTaxi: Bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.drawable.ic_taxi)
    }
    private val lazyPooling: Bitmap by lazy {
        BitmapFactory.decodeResource(resources, R.drawable.ic_pooling)
    }

    private var rateZoomMap: Int = 0

    private var map: GoogleMap? = null
    private var mapView: MapView? = null

    private var mapIsEmpty: String = ""
    private var headingIsEmpty: String = ""
    private var coordinateIsEmpty: String = ""

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    private lateinit var viewModel: Dz13ViewModel

    private val observerState = Observer<Dz13State> {
        when (it) {
            is Dz13State.LoadFailed -> {
                onError(it.throwable)
            }
            is Dz13State.Data -> {
                drawMarkersOnMap(it.list)
            }
        }
    }

    private val observerSelectedItem = Observer<Poi> {
        if (it != null)
            onItemClick(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        mapIsEmpty = resources.getString(R.string.map_is_empty)
        headingIsEmpty = resources.getString(R.string.heading_is_empty)
        coordinateIsEmpty = resources.getString(R.string.coordinate_is_empty)

        rateZoomMap = resources.getString(R.string.rate_zoom_map).toInt()

        viewModel = ViewModelProviders.of(this).get(Dz13ViewModel::class.java)

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9Container))

        mapView = findViewById(R.id.map)
        mapView?.let { itMapView ->
            itMapView.onCreate(savedInstanceState)
            itMapView.getMapAsync(this)
        }

        if (savedInstanceState == null) {
            val dz11Fragment = Dz13Fragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.dz9Container, dz11Fragment)
            transaction.commit()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map?.let { itMap ->
            itMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            itMap.uiSettings.isZoomControlsEnabled = true
        } ?: onError(Exception(mapIsEmpty))

        viewModelObserve()
    }

    private fun onItemClick(item: Poi) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        map?.clear() ?: onError(Exception(mapIsEmpty))

        item.coordinate?.let {
            val location = LatLng(it.latitude, it.longitude)
            placeMarkerOnMap(location, item.fleetType.toString())
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12f)) ?: onError(Exception(mapIsEmpty))
        } ?: onError(Exception(coordinateIsEmpty))
    }

    private fun placeMarkerOnMap(location: LatLng, fleetType: String) {
        val markerOptions = MarkerOptions().position(location)

        markerOptions.icon(
            BitmapDescriptorFactory.fromBitmap(
                if (fleetType == "TAXI") lazyTaxi
                else lazyPooling
            )
        )

        val titleString = getAddress(location)

        markerOptions.title(titleString)
        map?.addMarker(markerOptions) ?: onError(Exception(mapIsEmpty))
    }

    private fun getAddress(latLng: LatLng): String {
        val geoCoder = Geocoder(this)
        val addressList: List<Address>?
        var addressText = ""

        try {
            addressList = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

            if (addressList != null && addressList.isNotEmpty()) {
                addressText = addressList[0].getAddressLine(0)
            }
        } catch (e: IOException) {
            Log.e("Dz13MvvmActivity", e.message!!)
        }

        return addressText
    }

    private fun viewModelObserve() {
        viewModel.state.observeForever(observerState)
        viewModel.selectedItem.observeForever(observerSelectedItem)
    }

    @SuppressLint("MissingPermission")
    private fun drawMarkersOnMap(poiList: List<Poi>) {
        map?.let { itMap ->

            itMap.isMyLocationEnabled = true

            val builder = LatLngBounds.builder()
            poiList.forEach { itPoi ->
                itPoi.coordinate?.let {
                    val location = LatLng(it.latitude, it.longitude)

                    itPoi.heading?.let { heading ->
                        itMap.addMarker(
                            MarkerOptions().position(location)
                                .rotation(heading.toFloat())
                        )
                    } ?: onError(Exception(headingIsEmpty))

                    builder.include(location)
                } ?: onError(Exception(coordinateIsEmpty))
            }

            val bounds = builder.build()
            itMap.moveCamera(
                CameraUpdateFactory.newLatLngBounds(
                    bounds,
                    resources.displayMetrics.widthPixels,
                    resources.displayMetrics.heightPixels,
                    resources.displayMetrics.widthPixels / rateZoomMap
                )
            )
        } ?: onError(Exception(mapIsEmpty))
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
        viewModel.state.removeObserver(observerState)
        viewModel.selectedItem.removeObserver(observerSelectedItem)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }
}