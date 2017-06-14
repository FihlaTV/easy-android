@file:JvmName("ViewVisibilityUtils")

package it.facile.easyandroid

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

/** Set the View visibility to VISIBLE and eventually animate the View alpha till 100% */
@JvmOverloads fun View.visible(animate: Boolean = true) {
    if (animate) {
        animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

/** Set the View visibility to INVISIBLE and eventually animate view alpha till 0% */
@JvmOverloads fun View.invisible(animate: Boolean = true) {
    hide(View.INVISIBLE, animate)
}

/** Set the View visibility to GONE and eventually animate view alpha till 0% */
@JvmOverloads fun View.gone(animate: Boolean = true) {
    hide(View.GONE, animate)
}

/** Convenient method that chooses between View.visible() or View.invisible() methods */
@JvmOverloads fun View.visibleOrInvisible(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else invisible(animate)
}

/** Convenient method that chooses between View.visible() or View.gone() methods */
@JvmOverloads fun View.visibleOrGone(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else gone(animate)
}

private fun View.hide(hidingStrategy: Int, animate: Boolean = true) {
    if (animate) {
        animate().alpha(0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = hidingStrategy
            }
        })
    } else {
        visibility = hidingStrategy
    }
}