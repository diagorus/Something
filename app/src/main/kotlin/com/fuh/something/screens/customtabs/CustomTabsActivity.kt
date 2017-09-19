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
import com.fuh.something.utils.customtabs.CustomTabActivityHelper

/**
 * Created by nick on 19.09.17.
 */

class CustomTabsActivity: BaseToolbarActivity() {

    override fun getLayoutId(): Int = R.layout.customtabs_activity

    override fun ActionBar.init() {
        title = "Custom tabs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnCustomTabsGoToLink.setOnClickListener {
            val requestCode = 100

            val sendIntent = Intent()
                    .apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                        type = "text/plain"
                    }
            val chooserIntent = Intent.createChooser(sendIntent, "Share link")
            val pendingIntent = PendingIntent.getActivity(this, requestCode, chooserIntent, PendingIntent.FLAG_ONE_SHOT)

            val icon = BitmapFactory.decodeResource(resources, R.drawable.ic_share_black_24dp)

            val customTabsIntent = CustomTabsIntent.Builder()
                    .enableUrlBarHiding()
                    .setShowTitle(true)
                    .setActionButton(icon, "Share link", pendingIntent, true)
//                    .addMenuItem()
                    .setToolbarColor(ContextCompat.getColor(this, R.color.black))
                    .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.white))
                    .build()

            CustomTabActivityHelper.openCustomTab(this, customTabsIntent, Uri.parse("https://github.com/")) { activity, uri ->
                val intent = Intent(Intent.ACTION_VIEW, uri)
                activity.startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()


    }

    override fun onStop() {
        super.onStop()
    }
}