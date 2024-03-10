package com.example.androidrecap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import com.example.androidrecap.databinding.ActivityGoogleMapBinding
import com.example.androidrecap.utils.PermissionHandler
import com.example.androidrecap.viewModels.GPSViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class GoogleMapActivity : AppCompatActivity() {
    private val view: ActivityGoogleMapBinding by lazy {
        ActivityGoogleMapBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: GPSViewModel by viewModels()
    private lateinit var map: GoogleMap

    // create data class for landmark pins
    data class UniversityLandMark(
        val name: String,
        val latLng: LatLng,
        val color: Float,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        PermissionHandler.requestPermissionIfRequired(this, PermissionHandler.GPS)
        viewModel.getCurrentLocation()

        view.mvGoogleMap.onCreate(savedInstanceState)

        view.mvGoogleMap.getMapAsync {
            map = it
        }

        viewModel.userLocation.observe(this@GoogleMapActivity) {
            Log.d("DAT", "TRIGGEr")
            val latLng = LatLng(it.latitude, it.longitude)
            val currentLocation = LatLng(13.61241442027348, 100.83781907311533)

            // list of locations for pin

            val locations = listOf(
                UniversityLandMark(
                    "A",
                    LatLng(13.61635394692175, 100.83392680470457),
                    BitmapDescriptorFactory.HUE_AZURE
                ),
                UniversityLandMark(
                    "B",
                    LatLng(13.613850881068963, 100.83299964695479),
                    BitmapDescriptorFactory.HUE_CYAN
                ),
                UniversityLandMark(
                    "C",
                    LatLng(13.611481287659538, 100.84009068678196),
                    BitmapDescriptorFactory.HUE_RED
                )
            )
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16f))

            for (location in locations) {
                map.addMarker(
                    MarkerOptions()
                        .position(location.latLng)
//                        .icon(BitmapDescriptorFactory.defaultMarker(location.color))
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pikachu))
                        .icon(
                            BitmapDescriptorFactory.fromBitmap(
                                getBitmapIconSize(
                                    R.drawable.pikachu
                                )
                            )
                        )
                        .title(location.name)
                        .snippet("This is ${location.name}")

                )



                map.addCircle(
                    CircleOptions().center(currentLocation).radius(700.0).fillColor(Color.CYAN)
                )
                val polyOptions = PolylineOptions()
                for (location in locations) {
                    polyOptions.add(location.latLng)
                }
                map.addPolyline(polyOptions)

                val polygonOptions = PolygonOptions()
                    .add(
                        locations[0].latLng,
                        locations[1].latLng,
                        locations[2].latLng
                    )
                map.addPolygon(polygonOptions)

            }
        }
    }

    private fun getBitmapIconSize(id: Int): Bitmap {
        val bitmap = BitmapFactory.decodeResource(resources, id)
        return Bitmap.createScaledBitmap(bitmap, 200, 200, false)
    }


    override fun onStart() {
        super.onStart()
        view.mvGoogleMap.onStart()
    }

    override fun onResume() {
        super.onResume()
        view.mvGoogleMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        view.mvGoogleMap.onResume()
    }

    override fun onStop() {
        super.onStop()
        view.mvGoogleMap.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        view.mvGoogleMap.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        view.mvGoogleMap.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        view.mvGoogleMap.onSaveInstanceState(outState)
    }

}