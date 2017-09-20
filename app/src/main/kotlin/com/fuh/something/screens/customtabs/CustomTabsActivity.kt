package com.fuh.something.screens.customtabs

import android.app.PendingIntent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.ActionBar
import com.fuh.something.R
import com.fuh.something.utils.BaseToolbarActivity
import kotlinx.android.synthetic.main.customtabs_activity.*
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.content.Intent
import android.text.util.Linkify
import com.fuh.something.utils.customtabs.CustomTabActivityHelper
import com.fuh.something.utils.extensions.createBitmapFromVector

/**
 * Created by nick on 19.09.17.
 */

class CustomTabsActivity: BaseToolbarActivity() {

    private val customTabActivityHelper: CustomTabActivityHelper = CustomTabActivityHelper()

    override fun getLayoutId(): Int = R.layout.customtabs_activity

    override fun ActionBar.init() {
        title = "Custom tabs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val link = "https://github.com/"

        btnCustomTabsGoToLink.setOnClickListener {
            val pendingIntentShare =
                    CustomTabsActionBroadcastReceiver.getCreatePendingForAction(this, CustomTabsActionBroadcastReceiver.Action.SHARE)

            val pendingIntentCopyLink =
                    CustomTabsActionBroadcastReceiver.getCreatePendingForAction(this, CustomTabsActionBroadcastReceiver.Action.COPY_LINK)

            val icon = createBitmapFromVector(R.drawable.ic_share_black_24dp)

            val customTabsIntent = CustomTabsIntent.Builder()
                    .enableUrlBarHiding()
                    .setShowTitle(true)
                    .setActionButton(icon, "Share link", pendingIntentShare, true)
                    .addMenuItem("Copy link", pendingIntentCopyLink)
                    .setToolbarColor(ContextCompat.getColor(this, R.color.black))
                    .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.white))
                    .build()

            CustomTabActivityHelper.openCustomTab(this, customTabsIntent, Uri.parse(link)) { activity, uri ->
                val intent = Intent(Intent.ACTION_VIEW, uri)
                activity.startActivity(intent)
            }

//            Linkify.addLinks(tvCustomTabsLinkInText, Linkify.WEB_URLS)
        }
    }

    override fun onStart() {
        super.onStart()

        customTabActivityHelper.bindCustomTabsService(this)
    }

    override fun onStop() {
        super.onStop()

        customTabActivityHelper.unbindCustomTabsService(this)
    }
}