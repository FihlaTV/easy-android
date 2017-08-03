package it.facile.easyandroid

import android.content.Intent
import android.os.Bundle
import android.provider.Browser

fun Intent.addHeadersToUrl(headers: List<Pair<String, String>>) {
    if (headers.isEmpty()) return

    val bundle: Bundle = Bundle()
    headers.forEach {
        (headerKey, headerValue) ->
        bundle.putString(headerKey, headerValue)
    }
    putExtra(Browser.EXTRA_HEADERS, bundle)
}