package com.example.mapslibrary

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment(),OnMapReadyCallback {

    lateinit var rootView : View
    lateinit var mView : MapView
    lateinit var mMap : GoogleMap

    lateinit var geocoder: Geocoder
    lateinit var addresses: List<Address>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map, container, false)
        return rootView    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = map
        if(mView != null){
            mView.onCreate(null)
            mView.onResume()
            mView.getMapAsync(this)
        }

    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!

        var place = LatLng(19.296664, -99.185745)
        mMap.addMarker(MarkerOptions().position(place).title("My Marker").draggable(true))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place))

        geocoder = Geocoder(context, Locale.getDefault())
        realizarZoom(15f,mMap)
        mostrarInfoMarcador()

    }

    fun realizarZoom(zoomLvl:Float, mMap:GoogleMap){
        var zoom: CameraUpdate = CameraUpdateFactory.zoomTo(zoomLvl)
        mMap.animateCamera(zoom)

    }



    fun mostrarInfoMarcador(){
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener{
            override fun onMarkerDragEnd(p0: Marker?) {
                var lat = p0!!.position.latitude
                var lng = p0.position.longitude
                mostrarInformacion(lat, lng)
            }

            override fun onMarkerDragStart(p0: Marker?) {
            }

            override fun onMarkerDrag(p0: Marker?) {
            }

        })
    }

    fun mostrarInformacion(lat:Double, lng: Double){
        addresses = geocoder.getFromLocation(lat, lng, 1)

        var address = addresses.get(0).getAddressLine(0)
        var city = addresses.get(0).locality
        var state = addresses.get(0).adminArea
        var country = addresses.get(0).countryCode
        var postalCode = addresses.get(0).postalCode

        Toast.makeText(context, "Address: " + address + "\n" +
                "City: " + city + "\n" +
                "State: " + state + "\n" +
                "Country: " + country + "\n" +
                "Postal Code: " + postalCode + "\n"
            , Toast.LENGTH_LONG).show()
    }


}
