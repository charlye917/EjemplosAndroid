package com.charlye934.menucomidarealtimedatabase

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.menucomidarealtimedatabase.adapter.SimpleItemRecyclerViewAdapter
import com.charlye934.menucomidarealtimedatabase.model.Comida
import com.charlye934.menucomidarealtimedatabase.util.ComidaContent
import com.charlye934.menucomidarealtimedatabase.util.ComonConstants
import com.example.menucomidarealtimedatabase.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_comida_list.*
import kotlinx.android.synthetic.main.comida_list.*

class ComidaListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var comidas: List<Comida>
    private lateinit var comidaUpdate: Comida
    private lateinit var aaComida: ArrayAdapter<String>
    private val adapterFood = SimpleItemRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida_list)

        setUpView()
        onClickListener()
        setUpRecycerView(comida_list)
    }

    private fun setUpView(){
        setSupportActionBar(toolbar)
        toolbar.setTitle(title)
        configSpinner()
    }

    private fun onClickListener() {
        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_SHORT).setAction("Action", null).show()
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
        val reference = database.getReference(ComonConstants.PATH_FOOD)

        reference.push().setValue(comida)
    }

    private fun configSpinner() {
        spFood.onItemSelectedListener
        aaComida = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        aaComida.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spFood.adapter = aaComida
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        comidaUpdate = comidas[position]
        etName.setText(comidaUpdate.nombre)
        etPrice.setText(comidaUpdate.precio)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun setUpRecycerView(recyclerView: RecyclerView){

        comida_list.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = adapterFood
        }

        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference(ComonConstants.PATH_FOOD)

        reference.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val comida = dataSnapshot.getValue(Comida::class.java)
                comida!!.id = dataSnapshot.key.toString()
                if (!ComidaContent.ITEMS.contains(comida)) {
                    ComidaContent.addItem(comida)
                }
                adapterFood.setData(ComidaContent.ITEMS)
                adapterFood.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                val comida = dataSnapshot.getValue(Comida::class.java)
                comida!!.id = dataSnapshot.key.toString()
                if (ComidaContent.ITEMS.contains(comida)) {
                    ComidaContent.updateItem(comida)
                }
                adapterFood.setData(ComidaContent.ITEMS)

                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                val comida = dataSnapshot.getValue(Comida::class.java)
                comida!!.id = dataSnapshot.key.toString()
                if (ComidaContent.ITEMS.contains(comida)) {
                    ComidaContent.deleteItem(comida)
                }
                adapterFood.setData(ComidaContent.ITEMS)

                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                Toast.makeText(this@ComidaListActivity, "Moved", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ComidaListActivity, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        })
    }
    }

}