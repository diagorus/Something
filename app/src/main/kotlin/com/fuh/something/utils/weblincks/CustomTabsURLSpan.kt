package com.fuh.something.utils.weblincks

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Parcel
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.text.style.URLSpan
import android.view.View
import com.fuh.something.R
import com.fuh.something.utils.customtabs.CustomTabActivityHelper

class CustomTabsURLSpan : URLSpan {
    constructor(url: String) : super(url)

    constructor(src: Parcel) : super(src)

    override fun onClick(widget: View) {
//        val requestCode = 100
//
//        val sendIntent = Intent(Intent.ACTION_SEND)
//                .apply {
//                    type = "text/plain"
//                    putExtra(Intent.EXTRA_TEXT, url)
//                }
//        val chooserIntent = Intent.createChooser(sendIntent, "Share link")
//        val pendingIntent = PendingIntent.getActivity(this, requestCode, chooserIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val icon = BitmapFactory.decodeResource(resources, R.drawable.ic_share)
//
//        val customTabsIntent = CustomTabsIntent.Builder()
//                .enableUrlBarHiding()
//                .setShowTitle(true)
//                .setActionButton(icon, "Share link", pendingIntent, true)
////                    .addMenuItem()
//                .setToolbarColor(ContextCompat.getColor(this, R.color.black))
//                .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.white))
//                .build()
//
//        CustomTabActivityHelper.openCustomTab(this, customTabsIntent, Uri.parse(url)) { activity, uri ->
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            activity.startActivity(intent)
//        }
    }
}