@file:JvmName("DialogsUtils")

package it.facile.easyandroid

import android.app.Dialog

fun Dialog.showOrDismiss(show: Boolean) {
    if (show) show() else dismiss()
}