package com.charlye934.mapscustome

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var custome:CustomeMaps
    private val REQUEST_LOCATION_PERMISSION = 1
    private val latitude = 19.2970994
    private val longitude = -99.1867883
    private val homeLatLng = LatLng(latitude, longitude)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        custome = CustomeMaps(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.map_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.normal_map ->{
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }

        R.id.hybrid_map ->{
            map.mapType = GoogleMap.MAP_TYPE_HYBRID
            true
        }

        R.id.satellite_map ->{
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }

        R.id.terrain_map ->{
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val androidOverlay= custome.androidOverlay(homeLatLng)

        map = googleMap
        enableMyLocation()
        custome.myHome(homeLatLng, map)
        custome.setMapLongClick(map)
        custome.setPoiClick(map)
        custome.setMapStyle(map)

        map.addGroundOverlay(androidOverlay)
        map.isTrafficEnabled = true

        //map = googleMap
        //val sydney = LatLng(19.2970994, -99.1867883)
        //map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun isPermissionGranted(): Boolean{
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation(){
        if(isPermissionGranted()){
            map.isMyLocationEnabled = true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_LOCATION_PERMISSION){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                enableMyLocation()
            }
        }
    }

    companion object{
        val TAG = MapsActivity::class.java.simpleName
    }
}