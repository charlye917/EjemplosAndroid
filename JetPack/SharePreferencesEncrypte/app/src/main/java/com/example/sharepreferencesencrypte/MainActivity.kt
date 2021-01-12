package com.example.sharepreferencesencrypte
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.security.crypto.EncryptedSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ChangesFragments{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(OptionsSecurityJetPackFragment(),OptionsSecurityJetPackFragment.tag)
    }

    override fun startEncryptedSharedFragment() {
        changeFragment(EncryptedSharedFragment(),EncryptedSharedFragment.tag)
    }

    override fun startEncryptedFileFragment() {
        changeFragment(FilesFragment(), FilesFragment.tag)
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.genericFragment, fragment)
            .addToBackStack(tag)
            .commit()
    }
}
