package it.facile.easyandroid

import android.os.Build
import android.text.Html

fun String.asHtml() = if (Build.VERSION.SDK_INT >= 24) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
} else {
    Html.fromHtml(this)
}