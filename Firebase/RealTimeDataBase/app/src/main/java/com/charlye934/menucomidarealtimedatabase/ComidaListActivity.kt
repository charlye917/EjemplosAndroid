package com.charlye934.menucomidarealtimedatabase

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.menucomidarealtimedatabase.adapter.SimpleItemRecyclerViewAdapter
import com.charlye934.menucomidarealtimedatabase.firebase.FunctionFirebase
import com.charlye934.menucomidarealtimedatabase.firebase.FunctionsFirebaseImp
import com.charlye934.menucomidarealtimedatabase.model.Comida
import com.charlye934.menucomidarealtimedatabase.util.AlertDialog
import com.charlye934.menucomidarealtimedatabase.util.ComonConstants
import com.example.menucomidarealtimedatabase.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_comida_list.*
import kotlinx.android.synthetic.main.comida_list.*
import kotlinx.android.synthetic.main.message_alert_dialog.*

class ComidaListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var comidas: List<Comida>
    private lateinit var comidaUpdate: Comida
    private lateinit var aaComida: ArrayAdapter<String>
    private val adapterFood = SimpleItemRecyclerViewAdapter()
    private val database = FirebaseDatabase.getInstance()
    private val firebase: FunctionFirebase = FunctionsFirebaseImp()
    private val reference = database.getReference(ComonConstants.PATH_FOOD)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida_list)

        setUpView()
        onClickListener()
        setUpRecycerView()
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

        btnRefreshSpinner.setOnClickListener {
            onRefreshSpinnerClicked()
        }
    }

    private fun setUpRecycerView(){
        comida_list.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = adapterFood
        }

        firebase.setUpRecycerView(this, adapterFood)
    }

    private fun sendDataFood(){
        val comida = Comida(nombre = etName.text.toString(), precio = etPrice.text.toString())
        reference.push().setValue(comida)
        etName.text.clear()
        etPrice.text.clear()
    }

    private fun configSpinner() {
        spFood.onItemSelectedListener
        aaComida = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        aaComida.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spFood.adapter = aaComida
    }

    private fun onRefreshSpinnerClicked(){
        comidas = arrayListOf()
        aaComida.clear()
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val comida = snapshot.getValue(Comida::class.java)
                    comida!!.id = snapshot.key!!
                    (comidas as ArrayList<Comida>).add(comida)
                    aaComida.add(comida.nombre)

                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@ComidaListActivity, "Error al consultar comidas.",
                        Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_info -> {
                val tvCode = TextView(this)
                val params = LinearLayout
                        .LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                tvCode.apply {
                    layoutParams = params
                    gravity = Gravity.CENTER_HORIZONTAL
                    textSize = 28F
                }

                firebase.singleValueEvent(this, tvCode)
                AlertDialog.dialgoCode(this, "Mi codigo", "OK", tvCode)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        comidaUpdate = comidas[position]
        etName.setText(comidaUpdate.nombre)
        etPrice.setText(comidaUpdate.precio)
        Toast.makeText(this, "onItemSelect", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}