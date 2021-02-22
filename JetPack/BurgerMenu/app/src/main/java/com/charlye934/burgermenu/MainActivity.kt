package com.charlye934.burgermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.charlye934.burgermenu.fragments.ArrivalsFragment
import com.charlye934.burgermenu.fragments.DepartureFragment
import com.charlye934.burgermenu.fragments.HomeFragment
import com.charlye934.burgermenu.toolbar.ToolbarActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad(toolbar as Toolbar?)

        setNavDrawer()
        setUserHeaderInformation()

        fragmentTransaction(HomeFragment())
        navView.menu.getItem(0).isChecked = true
    }

    private fun setNavDrawer(){
        val toogle = ActionBarDrawerToggle(this, drawerLayoutID, _toolbar, R.string.open_drawer, R.string.close_drawer)
        toogle.isDrawerIndicatorEnabled = true
        drawerLayoutID.addDrawerListener(toogle)
        toogle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    private fun loadFragmentById(id: Int){
        when(id){
            R.id.nav_home -> { fragmentTransaction(HomeFragment()) }
            R.id.nav_arrivals ->{ fragmentTransaction(ArrivalsFragment()) }
            R.id.nav_departures ->{ fragmentTransaction(DepartureFragment())}
        }
    }

    private fun showMessageNavItemSelecetedById(id:Int){
        when(id){
            R.id.nav_profile -> { Toast.makeText(this, "NAV PROFILE", Toast.LENGTH_SHORT).show() }
            R.id.nav_settings ->{ Toast.makeText(this, "NAV SETTINGS", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun setUserHeaderInformation(){
        val name = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        name.let{ name.text = "CARLOS ARTEAGA" }
        email.let{ email.text = "charlye934@gmail.com" }
    }

    private fun fragmentTransaction(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelecetedById(item.itemId)
        loadFragmentById(item.itemId)
        drawerLayoutID.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayoutID.isDrawerOpen(GravityCompat.START)){
            drawerLayoutID.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}