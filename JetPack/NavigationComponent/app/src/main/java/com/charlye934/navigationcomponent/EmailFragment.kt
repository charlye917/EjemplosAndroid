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
import androidx.databinding.adapters.ViewBindingAdapter.setOnClick
import androidx.navigation.findNavController
import com.charlye934.navigationcomponent.databinding.FragmentEmailBinding

class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    private lateinit var name: String
    private lateinit var bundle: Bundle

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container,false)
        getData()
        onClick()
        return binding.root
    }

    private fun getData() {
        arguments?.let {
            name = it.getString("input_name").toString()
        }
    }

    private fun onClick() {
        binding.submitButton.setOnClickListener {
            validation(it)
        }
    }

    private fun validation(view: View) {
        if(!TextUtils.isEmpty(binding.emailEditText.text.toString())) {
            bundle = bundleOf(
                "input_email" to binding.emailEditText.text.toString(),
                "input_name" to name
            )
            view.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment, bundle)
        }
        else {
            Toast.makeText(activity, " Please, enter your email ", Toast.LENGTH_LONG).show()
        }

    }
}