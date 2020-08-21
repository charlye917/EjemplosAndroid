package com.charlye934.mapscustome

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import java.util.*

class CustomeMaps(private val context:Context) {
    //zoom in my home and add a marker
    fun myHome(homeLatLng: LatLng, map: GoogleMap){

        /*Type zoom
        1: World
        5: Landmass/continent
        10: City
        15: Streets
        20: Buildings*/
        val zoomLevel = 15f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
        map.addMarker(MarkerOptions().position(homeLatLng))

    }

    //Marker a locaiton
    fun setMapLongClick(map: GoogleMap){
        map.setOnMapClickListener { latLng ->
            //Add a info window for the marker
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )

            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(context.getString(R.string.dropped_pin))
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
        }
    }

    //Show a title place
    fun setPoiClick(map: GoogleMap){
        map.setOnPoiClickListener { poi ->
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            )

            poiMarker.showInfoWindow()
        }
    }

    //ADD STYLE MAP
    fun setMapStyle(map: GoogleMap){
        try{
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.map_style
                )
            )

            if(!success){
                Log.e(MapsActivity.TAG, "Style parsiong failed")
            }
        }catch(e: Resources.NotFoundException){
            Log.e(MapsActivity.TAG, "Can't find style. Error", e)
        }
    }

    fun androidOverlay(homeLatLng: LatLng): GroundOverlayOptions{
        val overlaySize = 100f

        return GroundOverlayOptions()
            .image(BitmapDescriptorFactory.fromResource(R.drawable.android))
            .position(homeLatLng, overlaySize)
    }
}