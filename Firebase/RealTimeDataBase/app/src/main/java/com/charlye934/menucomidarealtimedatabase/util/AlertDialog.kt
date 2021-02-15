package com.charlye934.menucomidarealtimedatabase.util

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView

object AlertDialog{
    fun dialgoCode(context: Context, title: String, positiveButton:String, textView: TextView){
        val builder = AlertDialog.Builder(context)
                .setTitle(title)
                .setPositiveButton(positiveButton, null)
        builder.setView(textView)
                .show()
    }
}