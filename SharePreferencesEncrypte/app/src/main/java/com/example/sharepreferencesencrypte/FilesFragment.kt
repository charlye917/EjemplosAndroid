package com.example.sharepreferencesencrypte

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import kotlinx.android.synthetic.main.fragment_files.*
import java.io.*
import java.lang.StringBuilder

class FilesFragment : Fragment() {

    private lateinit var normalFile: File
    private lateinit var secretFile: File
    private lateinit var encryptedFile: EncryptedFile

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_files, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        listeners()
    }

    private fun init() {
        normalFile = File(context!!.filesDir, "normal")
        secretFile = File(context!!.filesDir, "secret")

        encryptedFile = EncryptedFile.Builder(
            secretFile,
            context!!,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        )
            .setKeysetAlias("file_key")
            .setKeysetPrefName("secret_file_shared_prefs")
            .build()
    }

    private fun listeners() {
        bt_read_file.setOnClickListener { printFileContent(normalFile) }
        bt_show_content.setOnClickListener { printFileContent(secretFile) }

        bt_read_file_encrypted.setOnClickListener {
            if(!secretFile.exists())
                try {
                    secretFile.createNewFile()
                }catch (e:IOException){
                    e.printStackTrace()
                }
            try {
                encryptedFile.openFileInput().use {
                    tv_result.text = String(it.readBytes(), Charsets.UTF_8)
                }
            }catch (e:IOException){
                Toast.makeText(context, getString(R.string.file_not_found), Toast.LENGTH_SHORT).show()
            }
        }

        bt_save_file.setOnClickListener {
            if (!normalFile.exists())
                try {
                    normalFile.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            try {
                OutputStreamWriter(FileOutputStream(normalFile)).let {
                    it.write(et_data.text.toString())
                    it.close()
                }

                Toast.makeText(context, getString(R.string.file_saved), Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        bt_save_encrypted.setOnClickListener {
            secretFile.delete()

            encryptedFile.openFileOutput().use {
                it.write(et_data.text.toString().toByteArray())
                Toast.makeText(context, getString(R.string.file_saved), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun printFileContent(file: File) {
        if (file.exists()) {
            val strBuilder = StringBuilder()
            BufferedReader(FileReader(file)).readLines().forEach {
                strBuilder.append(it)
            }
            tv_result.text = strBuilder.toString()
        } else
            Toast.makeText(context, getString(R.string.file_not_found), Toast.LENGTH_SHORT).show()
    }

    companion object{
        val tag = this::class.java.simpleName
    }

}
