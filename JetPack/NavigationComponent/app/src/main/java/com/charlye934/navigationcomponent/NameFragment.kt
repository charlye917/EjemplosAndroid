package com.charlye934.navigationcomponent

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.charlye934.navigationcomponent.databinding.FragmentNameBinding

class NameFragment : Fragment() {

    private lateinit var bindig: FragmentNameBinding
    private lateinit var name: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindig = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        setOnclick()
        return bindig.root
    }

    private fun setOnclick() {
        bindig.nextButton.setOnClickListener {
            validation(it)
        }
    }

    private fun validation(it: View?) {
        if(!TextUtils.isEmpty(bindig.nameEditText.text.toString())){
            var bundle = bundleOf("input_name" to bindig.nameEditText.text.toString())
            it!!.findNavController().navigate(R.id.action_nameFragment2_to_emailFragment, bundle)
        }else{
            Toast.makeText(activity,"User Name cannot be empty", Toast.LENGTH_LONG).show()
        }
    }
}