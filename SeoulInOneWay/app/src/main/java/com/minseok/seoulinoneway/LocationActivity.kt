package com.minseok.seoulinoneway

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.minseok.seoulinoneway.common.ExtendedActivity
import com.minseok.seoulinoneway.common.LocationHelper

class LocationActivity : ExtendedActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        LocationHelper.checkCurrentLocation(this, object: LocationHelper.LocationCallback {
            override fun onSuccess(latitude: Double, longitude: Double) {
                val currentPosition = LatLng(latitude, longitude)
                googleMap.addMarker(MarkerOptions().position(currentPosition)
                        .title("현재위치"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 16.0f))
            }

            override fun onFailure() {
                toast("위치정보를 불러올 수 없습니다.")
            }
        })
    }
}
