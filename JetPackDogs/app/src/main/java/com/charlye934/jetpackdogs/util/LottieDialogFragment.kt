package com.charlye934.jetpackdogs.util

import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.charlye934.jetpackdogs.R

class LottieDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context
        val inflater = LayoutInflater.from(context)
        val rootView: View = inflater.inflate(R.layout.lottie_load, null, false)
        val lottieAnimationView: LottieAnimationView = rootView.findViewById(R.id.lottie_load)
        lottieAnimationView.setImageAssetsFolder("raw/")
        lottieAnimationView.setAnimation("load.json")
        lottieAnimationView.repeatCount = LottieDrawable.INFINITE
        lottieAnimationView.playAnimation()

        val builder = AlertDialog.Builder(context!!)
        builder.setView(rootView)
            .setCancelable(false)
        // Create the AlertDialog object and return it
        // Create the AlertDialog object and return it
        val alertDialog: Dialog = builder.create()

        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setOnKeyListener { _: DialogInterface?, keyCode: Int, event: KeyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP)
                Toast.makeText(getContext(), "Espere un momento…", Toast.LENGTH_SHORT).show()

            true
        }
        alertDialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alertDialog.window!!.navigationBarColor = ContextCompat.getColor(context!!, R.color.white)
        else
            alertDialog.window!!.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        return alertDialog
    }
}