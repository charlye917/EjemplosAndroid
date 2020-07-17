package com.charlye934.jetpackdogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.charlye934.jetpackdogs.R
import com.charlye934.jetpackdogs.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private val viewModel : DetailsViewModel by activityViewModels()
    private var dogUuid = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let {
                txtDogNameDetail.text = dog.dogBreed
                txtDogPurposeDetail.text = dog.bredFor
                txtDogTemplerament.text = dog.temperament
                txtDogLifeSpan.text = dog.lifeSpan

            }
        })
    }
}