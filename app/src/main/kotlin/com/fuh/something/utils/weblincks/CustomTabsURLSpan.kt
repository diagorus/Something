package com.fuh.something.utils.weblincks

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Parcel
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.text.style.URLSpan
import android.view.View
import com.fuh.something.R
import com.fuh.something.screens.customtabs.CustomTabsActionBroadcastReceiver
import com.fuh.something.utils.customtabs.CustomTabActivityHelper
import com.fuh.something.utils.extensions.createBitmapFromVector

class CustomTabsURLSpan: URLSpan {
    private val activity: Activity

    constructor(activity: Activity, url: String) : super(url) {
        this.activity = activity
    }

    constructor(activity: Activity, src: Parcel) : super(src) {
        this.activity = activity
    }

    override fun onClick(widget: View) {
        val pendingIntentShare =
                CustomTabsActionBroadcastReceiver.getCreatePendingForAction(activity, CustomTabsActionBroadcastReceiver.Action.SHARE)

        val pendingIntentCopyLink =
                CustomTabsActionBroadcastReceiver.getCreatePendingForAction(activity, CustomTabsActionBroadcastReceiver.Action.COPY_LINK)

        val shareIcon = activity.createBitmapFromVector(R.drawable.ic_share_black_24dp)
        val closeIcon = activity.createBitmapFromVector(R.drawable.ic_arrow_back_white_24dp)

        val customTabsIntent = CustomTabsIntent.Builder()
                .enableUrlBarHiding()
                .setShowTitle(true)
                .setActionButton(shareIcon, "Share link", pendingIntentShare, true)
                .setCloseButtonIcon(closeIcon)
                .addMenuItem("Copy link", pendingIntentCopyLink)
                .setToolbarColor(ContextCompat.getColor(activity, R.color.black))
                .setSecondaryToolbarColor(ContextCompat.getColor(activity, R.color.white))
                .setStartAnimations(activity.applicationContext, R.anim.slide_in_right, R.anim.slide_out_left)
                .setExitAnimations(activity.applicationContext, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .build()

        CustomTabActivityHelper.openCustomTab(activity, customTabsIntent, Uri.parse(url)) { activity, uri ->
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        }
    }
}