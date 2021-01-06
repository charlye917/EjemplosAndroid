package com.charlye934.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ActivityMainBinding
import com.charlye934.roomdemo.db.Subscriber
import com.charlye934.roomdemo.db.SubscriberDatabase
import com.charlye934.roomdemo.viewmodel.SubscriberViewModel
import com.charlye934.roomdemo.viewmodel.SubscriberViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapterRecycler: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val factory = SubscriberViewModelFactory(dao)

        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        subscriberViewModel.message.observe(this, Observer {event: Event<String> ->
            event.getContentIfNotHandled().let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

    }

   private fun initRecyclerView(){
       adapterRecycler = MyRecyclerViewAdapter { selectedItem: Subscriber -> listItemClicked(selectedItem) }
       binding.subscriberRecyclerView.apply {
           layoutManager = LinearLayoutManager(context)
           adapter = adapterRecycler
       }
       displaySubscribersList()
   }

    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapterRecycler.setList(it)
            adapterRecycler.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber){
        //Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}
