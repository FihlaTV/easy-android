@file:JvmName("FragmentsUtils")

package it.facile.easyandroid

import android.support.v4.app.Fragment

/** Returns the Fragment's parent that implements [T] looking first at parentFragment and then at
 * parentActivity. */
inline fun <reified T> Fragment.getParentAs(): T? {
    val parentFragment = parentFragment
    val parentActivity = activity
    return if (parentFragment is T) {
        parentFragment
    } else if (parentActivity is T) {
        parentActivity
    } else null
}