@file:JvmName("ActivitiesUtils")

package it.facile.easyandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.annotation.ColorRes
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.openKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, 0)
}

fun Activity.closeKeyboard() {
    currentFocus?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

@JvmOverloads fun Activity.openUrl(url: Uri, @ColorRes tabColor: Int? = null) {
    if (isChromeCustomTabsSupported()) {
        CustomTabsIntent.Builder().apply {
            tabColor?.let { setToolbarColor(ContextCompat.getColor(this@openUrl, it)) }
        }.build().launchUrl(this, url)
    } else {
        val browserIntent = Intent(Intent.ACTION_VIEW, url).apply {
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }
        startActivity(browserIntent)
    }
}

/**
 * Extension function that generates a Lazy property that query the Activity Intent with a given key
 * and returns the corresponding object.
 *
 * @param key The key of the object
 *
 * @return the object of type T, null if no object is not found
 */
fun <T> Activity.intentExtras(key: String): Lazy<T?> = lazy(LazyThreadSafetyMode.NONE) {
    @Suppress("UNCHECKED_CAST")
    intent.extras.get(key) as T
}

/**
 * Extension function that generates a Lazy property that query the Activity Intent with a given key
 * and returns the corresponding object.
 *
 * @param key The key of the object
 *
 * @return the object of type T
 * @throws MissingRequiredIntentExtraException if the key is missing
 */
fun <T> Activity.intentExtrasRequired(key: String): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    @Suppress("UNCHECKED_CAST")
    (intent.extras.get(key) ?: throw MissingRequiredIntentExtraException(key)) as T
}

class MissingRequiredIntentExtraException(key: String) : Exception("The Intent extra with key $key is missing")