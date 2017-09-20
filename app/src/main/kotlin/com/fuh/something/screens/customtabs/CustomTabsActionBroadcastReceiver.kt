package com.fuh.something.screens.customtabs

import android.app.PendingIntent
import android.widget.Toast
import com.fuh.something.utils.extensions.toast
import android.R.attr.label
import android.content.*


class CustomTabsActionBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val KEY_ACTION_SOURCE = "KEY_ACTION_SOURCE"

        fun getCreatePendingForAction(context: Context, action: Action): PendingIntent {
            val actionId = action.id
            val applicationContext = context.applicationContext

            val intent = Intent(applicationContext, CustomTabsActionBroadcastReceiver::class.java)
                    .apply {
                        putExtra(CustomTabsActionBroadcastReceiver.KEY_ACTION_SOURCE, actionId)
                    }

            return PendingIntent.getBroadcast(
                    applicationContext,
                    actionId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val url = intent.dataString
        url?.let {
            val actionId = intent.getIntExtra(KEY_ACTION_SOURCE, -1)
            val action = Action.findActionById(actionId)

            when(action) {
                Action.SHARE -> openShare(context, url)
                Action.COPY_LINK -> copyToClipboard(context, url)
                else -> showUnknownOperation(context)
            }
        }
    }

    private fun openShare(context: Context, url: String) {
        val sendIntent = Intent(Intent.ACTION_SEND)
                .apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, url)
                }

        context.startActivity(
                Intent.createChooser(sendIntent, "Share link").apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        )
    }

    private fun copyToClipboard(context: Context, url: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(null, url)
        clipboard.primaryClip = clip
        context.toast("Link copied to clipboard")
    }

    private fun showUnknownOperation(context: Context) {
        context.toast("Unknown operation")
    }

    enum class Action(val id: Int) {
        SHARE(1),
        COPY_LINK(2);

        companion object {
            fun findActionById(id: Int): Action? = Action.values().find { it.id == id }
        }
    }
}