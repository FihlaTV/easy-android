package it.facile.easyandroid

import android.content.Intent
import android.os.Bundle
import android.provider.Browser

fun Intent.putBrowserHeadersExtra(headers: List<Pair<String, String>>) {
    if (headers.isEmpty()) return

    val bundle: Bundle = Bundle()
    headers.forEach { (key, value) -> bundle.putString(key, value) }
    putExtra(Browser.EXTRA_HEADERS, bundle)
}