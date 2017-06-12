package it.facile.easyandroid

import android.util.Log

/* Android Log functions with Tag as caller class name */

inline fun <reified T: Any> T.logD(text: String) {
    Log.d(this::class.java.simpleName, text)
}

inline fun <reified T: Any> T.logE(text: String) {
    Log.e(this::class.java.simpleName, text)
}

inline fun <reified T: Any> T.logI(text: String) {
    Log.i(this::class.java.simpleName, text)
}

inline fun <reified T: Any> T.logV(text: String) {
    Log.v(this::class.java.simpleName, text)
}

inline fun <reified T: Any> T.logW(text: String) {
    Log.w(this::class.java.simpleName, text)
}