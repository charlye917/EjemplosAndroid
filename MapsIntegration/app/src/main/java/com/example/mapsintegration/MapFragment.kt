package com.example.mapsintegration

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import java.util.*
import kotlin.math.ln

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    lateinit var rootView : View
    lateinit var mView : MapView
    lateinit var mMap : GoogleMap

    lateinit var geocoder: Geocoder
    lateinit var addresses: List<Address>

    lateinit var marker: Marker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map, container, false)
        return rootView
    }

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
        realizarZoom(15f)
        mostrarInfoMarcador()
        val sucursales: List<LatLng> = listOf(LatLng(0.0,0.0), LatLng(10.0,10.0), LatLng(20.0,20.0))

        sucursales.size //Muestra el tamaño de la lista
        //sucursales.get(3) //Devuelve el valor de la posición 3
        sucursales.first() //Devuelve el primer valor
        sucursales.last() //Devuelve el último valor
        println(sucursales) //[Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo]
        mostrarMarcadores(sucursales, mMap)


        mMap.setOnMarkerClickListener(object : OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                var lt = marker.position.latitude
                var lg = marker.position.longitude
                var place = LatLng(lt, lg)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
                return false
            }
        })
        /*mMap.setOnMarkerClickListener(object : OnMarkerClickListener)

        mMap.setOnMarkerClickListener {
            var lt = it.position.latitude
            var lg = it.position.longitude
            var place = LatLng(lt, lg)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        }*/


    }

    fun realizarZoom(zoomLvl:Float){
        var zoom:CameraUpdate = CameraUpdateFactory.zoomTo(zoomLvl)
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

    fun mostrarMarcadores(listaSucursales: List<LatLng>, mMap:GoogleMap){

        for (i in  listaSucursales){
            mMap.addMarker(MarkerOptions().position(i).title("My Marker"))
        }


    }

}
