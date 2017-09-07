@file:JvmName("ActivitiesUtils")

package it.facile.easyandroid

import android.app.Activity
import android.content.ActivityNotFoundException
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

@JvmOverloads fun Activity.openUrl(url: Uri, @ColorRes tabColor: Int? = null, headers: List<Pair<String, String>> = emptyList()) =
        try {
            if (isChromeCustomTabsSupported()) {
                CustomTabsIntent.Builder()
                        .apply { tabColor?.let { setToolbarColor(ContextCompat.getColor(this@openUrl, it)) } }
                        .build()
                        .apply { intent.putBrowserHeadersExtra(headers) }
                        .launchUrl(this, url)
                true
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW, url).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    putBrowserHeadersExtra(headers)
                }
                startActivity(browserIntent)
                true
            }
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            false
        }

/**
 * Extension function that generates a Lazy property that queries the Activity Intent with a given key
 * and returns the corresponding object.
 *
 * @param key The key of the object
 *
 * @return the object of type T, null if no object is not found
 */
fun <T> Activity.intentExtras(key: String): Lazy<T?> = lazy(LazyThreadSafetyMode.NONE) {
    @Suppress("UNCHECKED_CAST")
    getIntentExtra<T>(key)
}

/**
 * Extension function that generates a Lazy property that queries the Activity Intent with a given key
 * and returns the corresponding object.
 *
 * @param key The key of the object
 *
 * @return the object of type T
 * @throws MissingRequiredIntentExtraException if the key is missing
 */
fun <T> Activity.intentExtrasRequired(key: String): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    @Suppress("UNCHECKED_CAST")
    getIntentExtraRequired<T>(key)
}

/**
 * Extension function that queries the Activity Intent with a given key and returns the corresponding object.
 *
 * @param key The key of the object
 *
 * @return the object of type T, null if no object is not found
 */
@Suppress("UNCHECKED_CAST")
fun <T> Activity.getIntentExtra(key: String): T? =
        intent?.extras?.get(key) as T?

/**
 * Extension function that queries the Activity Intent with a given key and returns the corresponding object.
 *
 * @param key The key of the object
 *
 * @return the object of type T
 * @throws MissingRequiredIntentExtraException if the key is missing
 */
@Suppress("UNCHECKED_CAST")
fun <T> Activity.getIntentExtraRequired(key: String): T =
        (intent.extras.get(key) ?: throw MissingRequiredIntentExtraException(key)) as T

class MissingRequiredIntentExtraException(key: String) : Exception("The Intent extra with key $key is missing")
