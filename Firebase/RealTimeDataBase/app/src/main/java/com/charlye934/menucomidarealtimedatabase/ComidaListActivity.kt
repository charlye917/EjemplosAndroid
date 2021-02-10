package com.charlye934.menucomidarealtimedatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.menucomidarealtimedatabase.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_comida_list.*

class ComidaListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val PATH_FOOD = "food"
    private val PATH_PROFILE = "profile"
    private val PATH_CODE = "code"

    private lateinit var comidas: List<Comida>
    private lateinit var comidaUpdate: Comida
    private lateinit var aaComida: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida_list)

        setUpView()
        onClickListener()
        setUpRecyclerView()

    }

    @SuppressLint("ResourceAsColor", "ResourceType")
    private fun setUpView(){
        setSupportActionBar(toolbar)
        toolbar.setTitle(title)

        //configSpinner()
    }

    private fun onClickListener() {
        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }

        btnSave.setOnClickListener {
            sendDataFood()
        }
    }



    private fun sendDataFood(){
        val comida = Comida(
            nombre = etName.text.toString(),
            precio = etPrice.text.toString()
        )

        if(comida != null){}

        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference(PATH_FOOD)

        reference.push().setValue(comida)
    }

    private fun configSpinner() {
        spFood.onItemSelectedListener
        aaComida = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        aaComida.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        comidaUpdate = comidas[position]
        etName.setText(comidaUpdate.nombre)
        etPrice.setText(comidaUpdate.precio)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}