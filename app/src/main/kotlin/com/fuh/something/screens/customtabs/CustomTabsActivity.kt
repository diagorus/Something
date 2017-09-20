package com.fuh.something.screens.customtabs

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.ActionBar
import com.fuh.something.R
import com.fuh.something.utils.BaseToolbarActivity
import kotlinx.android.synthetic.main.customtabs_activity.*
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.content.Intent
import android.text.method.LinkMovementMethod
import com.fuh.something.utils.customtabs.CustomTabActivityHelper
import com.fuh.something.utils.extensions.createBitmapFromVector
import com.fuh.something.utils.weblincks.LinkTransformationMethod
import android.text.util.Linkify



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

        tvCustomTabsLinkInText.transformationMethod = LinkTransformationMethod(
                this,
                Linkify.WEB_URLS or Linkify.PHONE_NUMBERS
        )

        btnCustomTabsGoToLink.setOnClickListener {
            val pendingIntentShare =
                    CustomTabsActionBroadcastReceiver.getCreatePendingForAction(this, CustomTabsActionBroadcastReceiver.Action.SHARE)

            val pendingIntentCopyLink =
                    CustomTabsActionBroadcastReceiver.getCreatePendingForAction(this, CustomTabsActionBroadcastReceiver.Action.COPY_LINK)

            val shareIcon = createBitmapFromVector(R.drawable.ic_share_black_24dp)
            val closeIcon = createBitmapFromVector(R.drawable.ic_arrow_back_white_24dp)

            val customTabsIntent = CustomTabsIntent.Builder()
                    .enableUrlBarHiding()
                    .setShowTitle(true)
                    .setActionButton(shareIcon, "Share link", pendingIntentShare, true)
                    .setCloseButtonIcon(closeIcon)
                    .addMenuItem("Copy link", pendingIntentCopyLink)
                    .setToolbarColor(ContextCompat.getColor(this, R.color.black))
                    .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.white))
                    .setStartAnimations(applicationContext, R.anim.slide_in_right, R.anim.slide_out_left)
                    .setExitAnimations(applicationContext, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .build()

            CustomTabActivityHelper.openCustomTab(this, customTabsIntent, Uri.parse(link)) { activity, uri ->
                val intent = Intent(Intent.ACTION_VIEW, uri)
                activity.startActivity(intent)
            }
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