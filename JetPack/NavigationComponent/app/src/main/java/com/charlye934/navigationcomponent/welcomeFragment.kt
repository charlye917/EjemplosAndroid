package com.charlye934.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.charlye934.navigationcomponent.databinding.FragmentWelcomeBinding
import kotlinx.android.synthetic.*

class welcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var name: String
    private lateinit var email: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        getData()
        setData()
        onClick()
        return binding.root
    }

    private fun getData(){
        arguments?.let {
            name = it.getString("input_name").toString()
            email = it.getString("input_email").toString()
        }
    }

    private fun setData(){
        binding.nameTextView.text = name
        binding.emailTextView.text = email
    }

    private fun onClick(){
        binding.viewTermsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_welcomeFragment_to_termsFragment)
        }
    }
}