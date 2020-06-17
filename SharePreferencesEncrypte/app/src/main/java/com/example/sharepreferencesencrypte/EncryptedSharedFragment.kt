package com.example.sharepreferencesencrypte

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.android.synthetic.main.fragment_encrypted_shared.*
import java.io.File

class EncryptedSharedFragment : Fragment() {

    private val preferencesName = "SharedPreferences"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_encrypted_shared, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEncrypted.setOnCheckedChangeListener { _, checked -> initSharedPreferences(checked) }
        saveButton.setOnClickListener { saveValue() }
        readButton.setOnClickListener { readValue() }

        initEncrypted.isChecked = true
    }

    private fun initSharedPreferences(checked: Boolean) {
        resetSharedPreferences()

        if (checked) {
            initEncryptedSharedPreferences()
        } else {
            sharedPreferences = context!!.getSharedPreferences(preferencesName,
                AppCompatActivity.MODE_PRIVATE
            )
        }

        showRawFile()
    }

    private fun resetSharedPreferences() {
        context!!.getSharedPreferences(preferencesName, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }

    private fun initEncryptedSharedPreferences() {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        sharedPreferences = EncryptedSharedPreferences.create(
            preferencesName,
            masterKeyAlias,
            context!!,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun saveValue() {
        sharedPreferences.edit()
            .putString("DATA", saveText.text.toString())
            .apply()

        showRawFile()
    }

    private fun readValue() {
        val value = sharedPreferences.getString("DATA", "")
        readText.setText(value)

        showRawFile()
    }

    private fun showRawFile() {
        val preferencesFile = File("${context!!.applicationInfo.dataDir}/shared_prefs/$preferencesName.xml")
        if (preferencesFile.exists()) {
            fileText.text = preferencesFile.readText().highlight()
        } else {
            fileText.text = ""
        }
    }

    companion object{
        val tag = this::class.java.simpleName
    }

}
