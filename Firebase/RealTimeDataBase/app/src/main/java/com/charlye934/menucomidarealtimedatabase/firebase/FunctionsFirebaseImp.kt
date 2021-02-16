package com.charlye934.menucomidarealtimedatabase.firebase

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.menucomidarealtimedatabase.adapter.SimpleItemRecyclerViewAdapter
import com.charlye934.menucomidarealtimedatabase.model.Comida
import com.charlye934.menucomidarealtimedatabase.util.ComidaContent
import com.charlye934.menucomidarealtimedatabase.util.ComonConstants
import com.google.firebase.database.*

class FunctionsFirebaseImp : FunctionFirebase{

    private val database = FirebaseDatabase.getInstance()
    private val reference = database.getReference(ComonConstants.PATH_FOOD)
    private val referencesCode = database.getReference(ComonConstants.PATH_PROFILE).child(ComonConstants.PATH_CODE)

    override fun deleteFirebase(comida: Comida) {
        reference.child(comida.id).removeValue()
    }

    override fun singleValueEvent(context: Context, textView: TextView) {
        referencesCode.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                textView.text = dataSnapshot.value.toString()
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "No se pudo cargar el codigo", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun setUpRecycerView(context:Context, adapter: SimpleItemRecyclerViewAdapter ) {
        reference.addChildEventListener(object : ChildEventListener {

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val comida = dataSnapshot.getValue(Comida::class.java)
                comida!!.id = dataSnapshot.key!!
                if (!ComidaContent.ITEMS.contains(comida))
                    ComidaContent.addItem(comida)

                adapter.setData(ComidaContent.ITEMS)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                val comida = dataSnapshot.getValue(Comida::class.java)
                comida!!.id = dataSnapshot.key.toString()

                if(ComidaContent.ITEM_MAP.containsKey(comida.id))
                    ComidaContent.updateItem(comida)

                adapter.setData(ComidaContent.ITEMS)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                val comida = dataSnapshot.getValue(Comida::class.java)
                comida!!.id = dataSnapshot.key.toString()
                if (ComidaContent.ITEMS.contains(comida)) {
                    ComidaContent.deleteItem(comida)
                }
                adapter.setData(ComidaContent.ITEMS)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                Toast.makeText(context, "Moved", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        })
    }
}