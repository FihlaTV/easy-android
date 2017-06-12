package it.facile.easyandroid

import android.content.Context
import android.content.Intent

private val SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService"
private val CHROME_PACKAGE = "com.android.chrome"

fun Context.isChromeCustomTabsSupported() = Intent(SERVICE_ACTION).let {
    it.`package` = CHROME_PACKAGE
    packageManager.queryIntentServices(it, 0)?.isNotEmpty() ?: false
}