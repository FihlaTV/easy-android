@file:JvmName("SnackbarsUtils")

package it.facile.easyandroid

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

@JvmOverloads fun View.createSnackbar(text: String,
                        duration: Int = Snackbar.LENGTH_SHORT,
                        actionLabel: String = "Action",
                        dismissOnAction: Boolean = true,
                        action: ((View) -> Unit)? = null): Snackbar {

    val snackbar = Snackbar.make(this, text, duration)
    action?.let {
        snackbar.setAction(actionLabel, {
            action(it)
            if (dismissOnAction) snackbar.dismiss()
        })
    }
    return snackbar
}

@JvmOverloads fun View.createSnackbar(@StringRes resId: Int,
                        duration: Int = Snackbar.LENGTH_SHORT,
                        actionLabel: String = "Action",
                        dismissOnAction: Boolean = true,
                        action: ((View) -> Unit)? = null): Snackbar {
    val text = context.getText(resId).toString()
    return createSnackbar(text, duration, actionLabel, dismissOnAction, action)
}

@JvmOverloads fun View.showSnackbar(text: String,
                      duration: Int = Snackbar.LENGTH_SHORT,
                      actionLabel: String = "Action",
                      dismissOnAction: Boolean = true,
                      action: ((View) -> Unit)? = null) {
    createSnackbar(text, duration, actionLabel, dismissOnAction, action).show()
}

@JvmOverloads fun View.showSnackbar(@StringRes resId: Int,
                      duration: Int = Snackbar.LENGTH_SHORT,
                      actionLabel: String = "Action",
                      dismissOnAction: Boolean = true,
                      action: ((View) -> Unit)? = null) {
    createSnackbar(resId, duration, actionLabel, dismissOnAction, action).show()
}

/** Convenient method that shows or hides a Snackbar */
fun Snackbar.showOrDismiss(show: Boolean) {
    if (show) show() else dismiss()
}