@file:JvmName("DimensionsUtils")

package it.facile.easyandroid

import android.content.res.Resources

fun Int.dpToPx(): Int = (Resources.getSystem().displayMetrics.density * this).toInt()

fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.spToPx(): Int = (Resources.getSystem().displayMetrics.scaledDensity * this).toInt()

fun Int.pxToSp(): Int = (this / Resources.getSystem().displayMetrics.scaledDensity).toInt()

