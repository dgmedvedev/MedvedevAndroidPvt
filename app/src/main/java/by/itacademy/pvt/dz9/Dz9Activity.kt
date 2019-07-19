package by.itacademy.pvt.dz9

import android.content.pm.PackageManager
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

    private lateinit var map: GoogleMap
    private lateinit var mapView: MapView

    private val carRepository: CarRepository = provideCarRepository()
    private val poiList: MutableList<Poi> = mutableListOf()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        if (savedInstanceState == null) {
            val dz9Fragment = Dz9CarListFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.dz9Container, dz9Fragment)
            transaction.commit()
        }

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9Container))

        mapView = findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

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
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        map.uiSettings.isZoomControlsEnabled = true
    }

    override fun onCarClick(item: Poi) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        map.clear()
        val car = LatLng(
            item.coordinate?.latitude!!,
            item.coordinate.longitude
        )

        placeMarkerOnMap(car, item.fleetType.toString())
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(car, 12f))
    }

    override fun onSuccess(data: List<Poi>) {
        poiList.addAll(data)
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
        map.isMyLocationEnabled = true

        val builder = LatLngBounds.builder()
        poiList.forEach {
            val coord = LatLng(it.coordinate?.latitude!!, it.coordinate.longitude)
            map.addMarker(
                MarkerOptions().position(coord)
                    .rotation(it.heading!!.toFloat())
            )
            builder.include(coord)
        }

        val bounds = builder.build()
        map.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                resources.displayMetrics.widthPixels / 5
            )
        )
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show()
    }

    private fun placeMarkerOnMap(location: LatLng, fleetType: String) {
        val markerOptions = MarkerOptions().position(location)

        markerOptions.icon(
            BitmapDescriptorFactory.fromBitmap(
                if (fleetType == "TAXI")
                    BitmapFactory.decodeResource(resources, R.drawable.ic_taxi)
                else
                    BitmapFactory.decodeResource(resources, R.drawable.ic_pooling)
            )
        )

        val titleString = getAddress(location)

        markerOptions.title(titleString)
        map.addMarker(markerOptions)
    }

    private fun getAddress(latLng: LatLng): String {
        val geocoder = Geocoder(this)
        val addressList: List<Address>?
        var addressText = ""

        try {
            addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

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
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}