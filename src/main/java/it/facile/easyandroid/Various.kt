@file:JvmName("VariousUtils")

package it.facile.easyandroid

import android.net.Uri
import android.os.Handler

fun delay(delay: Long, f: () -> Unit) {
    Handler().postDelayed(f, delay)
}

fun String.toUri() = Uri.parse(this)