package by.itacademy.pvt.dz9

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.dz9.entity.Poi
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.IOException

class Dz9Activity : FragmentActivity(), OnMapReadyCallback, Dz9CarListFragment.ClickListener, CarRepositoryResult {

    private val poiList: MutableList<Poi> = mutableListOf()
    private val carRepository: CarRepository = provideCarRepository()

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
    private var mapViewIsEmpty: String = ""
    private var headingIsEmpty: String = ""
    private var coordinateIsEmpty: String = ""

    private var onSuccessCompleted = false
    private var onMapReadyCompleted = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        mapIsEmpty = resources.getString(R.string.map_is_empty)
        mapViewIsEmpty = resources.getString(R.string.mapView_is_empty)
        headingIsEmpty = resources.getString(R.string.heading_is_empty)
        coordinateIsEmpty = resources.getString(R.string.coordinate_is_empty)

        rateZoomMap = resources.getString(R.string.rate_zoom_map).toInt()

        if (savedInstanceState == null) {
            val dz9Fragment = Dz9CarListFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.dz9Container, dz9Fragment)
            transaction.commit()
        }

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9Container))

        mapView = findViewById(R.id.map)
        mapView?.let { itMapView ->
            itMapView.onCreate(savedInstanceState)
            itMapView.getMapAsync(this)
        } ?: onError(Exception(mapViewIsEmpty))

        carRepository
            .getCarByCoordinate(
                CoordinateParams
                    (
                    Coordinate(2342.0, 342.0),
                    Coordinate(3242.0, 3453.0)
                ), this
            )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map?.let { itMap ->
            itMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            itMap.uiSettings.isZoomControlsEnabled = true
        } ?: onError(Exception(mapIsEmpty))

        onMapReadyCompleted = true

        if (onSuccessCompleted)
            drawMarkersOnMap()
    }

    override fun onSuccess(data: List<Poi>) {
        poiList.addAll(data)

        onSuccessCompleted = true

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        if (onMapReadyCompleted) {
            drawMarkersOnMap()
        }
    }

    override fun onItemClick(item: Poi) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        map?.clear() ?: onError(Exception(mapIsEmpty))

        item.coordinate?.let {
            val location = LatLng(it.latitude, it.longitude)
            placeMarkerOnMap(location, item.fleetType.toString())
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12f)) ?: onError(Exception(mapIsEmpty))
        } ?: onError(Exception(coordinateIsEmpty))
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
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

    @SuppressLint("MissingPermission")
    private fun drawMarkersOnMap() {
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
            Log.e("Dz9Activity", e.message!!)
        }

        return addressText
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