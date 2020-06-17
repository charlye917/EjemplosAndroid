package com.example.sharepreferencesencrypte

import android.app.Activity
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun String.highlight(): Spanned {
    var high = this.replace("<([^>/]*)/>".toRegex(), "&lt;~blue~$1~/~/&gt;")
    high = high.replace("<([^>]*)>".toRegex(), "&lt;~blue~$1~/~&gt;")
    high = high.replace("([\\w]+)=\"([^\"]*)\"".toRegex(), "~red~$1~/~~black~=\"~/~~green~$2~/~~black~\"~/~")
    high = high.replace("~([a-z]+)~".toRegex(), "<span style=\"color: $1;\">")
    high = high.replace("~/~", "</span>")

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(high, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(high)
    }
}
