package com.example.sharepreferencesencrypte

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_options_security_jet_pack.*

class OptionsSecurityJetPackFragment : Fragment(), View.OnClickListener {

    lateinit var changesFragments: ChangesFragments

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_options_security_jet_pack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_shared_prefs.setOnClickListener(this)
        bt_files.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.bt_shared_prefs -> { changesFragments.startEncryptedSharedFragment() }
            R.id.bt_files -> {changesFragments.startEncryptedFileFragment()}
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        changesFragments = activity as MainActivity
    }

    companion object{
        val tag = this::class.java.simpleName
    }

}
