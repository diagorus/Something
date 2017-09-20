package com.fuh.something.utils.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.graphics.drawable.DrawableCompat
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.view.LayoutInflater
import android.view.View
import android.view.WindowId
import android.widget.Toast

/**
 * Created by nick on 19.09.17.
 */

fun Context.createBitmapFromVector(@DrawableRes vectorId: Int): Bitmap {
    val vector = AppCompatResources.getDrawable(this, vectorId)
    val icon = Bitmap.createBitmap(vector?.intrinsicWidth?:0, vector?.intrinsicHeight?:0, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(icon)
    vector?.setBounds(0, 0, canvas.width, canvas.height)
    vector?.draw(canvas)
    return icon
}

fun Context.createBitmapFromLayout(@LayoutRes layoutId: Int): Bitmap {
    val cluster = LayoutInflater.from(this).inflate(layoutId, null)

    cluster.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
    cluster.layout(-1, -1, cluster.measuredWidth, cluster.measuredHeight)

    val clusterBitmap = Bitmap.createBitmap(cluster.measuredWidth,
            cluster.measuredHeight, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(clusterBitmap)
    cluster.draw(canvas)

    return clusterBitmap
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}