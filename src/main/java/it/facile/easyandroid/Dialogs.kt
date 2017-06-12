@file:JvmName("DialogsUtils")

package it.facile.easyandroid

import android.app.Dialog

fun Dialog.showOrHide(show: Boolean) {
    if (show) show() else dismiss()
}