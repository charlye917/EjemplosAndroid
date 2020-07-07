package com.example.mapslibrary

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import java.util.*

class MapsFunctions {

    /**
     * Permite realizar zoom al nivel que le sea indicado por el usuario
     */
    fun realizarZoom(zoomLvl:Float, mMap: GoogleMap){
        var zoom: CameraUpdate = CameraUpdateFactory.zoomTo(zoomLvl)
        mMap.animateCamera(zoom)
    }


    /**
     * Recibe una lista de sucursales las cuales serán enviadas como objetos LatLng
     */
    fun mostrarMarcadores(listaSucursales: List<LatLng>, mMap: GoogleMap){
        for (i in  listaSucursales){
            mMap.addMarker(MarkerOptions().position(i).title("Sucursal BAZ").icon(
                BitmapDescriptorFactory
                .defaultMarker(90.0F)))
        }
    }


    /**
     * Realiza una animación de cámara la cual tiene un zoom = 15 y tilt = 30
     */
    fun moverCamara(mMap: GoogleMap, marker: Marker) {
        var lt = marker.position.latitude
        var lg = marker.position.longitude
        var place = LatLng(lt, lg)
        var camera = CameraPosition.builder()
            .target(place)
            .zoom(15f)
            .bearing(0f)
            .tilt(30f)
            .build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera))
    }

    /**
     * Cada vez que el usuario mueve el marcador este envia la información de la dirección
     * en la que se detuvo
     */
    fun mostrarInfoMarcador(context: Context, mMap: GoogleMap){
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener{
            override fun onMarkerDragEnd(p0: Marker?) {
                var lat = p0!!.position.latitude
                var lng = p0.position.longitude
                mostrarInformacion(lat, lng, context)
            }

            override fun onMarkerDragStart(p0: Marker?) {
            }

            override fun onMarkerDrag(p0: Marker?) {
            }

        })
    }

    private fun calcularSucursalesCercanas(marker:Marker){}

    /**
     * Toast para mostrar información
     */
    fun mostrarInformacion(lat:Double, lng: Double, context:Context){
        var geocoder = Geocoder(context, Locale.getDefault())
        var addresses = geocoder.getFromLocation(lat, lng, 1)

        var address = addresses.get(0).getAddressLine(0)
        var city = addresses.get(0).locality
        var state = addresses.get(0).adminArea
        var country = addresses.get(0).countryCode
        var postalCode = addresses.get(0).postalCode

        Toast.makeText(context, "Dirección: " + address + "\n" +
                "Ciudad: " + city + "\n" +
                "Estado: " + state + "\n" +
                "Pais: " + country + "\n" +
                "Codigo postal: " + postalCode + "\n"
            , Toast.LENGTH_LONG).show()
    }





}