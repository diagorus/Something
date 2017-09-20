package com.fuh.something.utils.weblincks

import android.app.Activity
import android.graphics.Rect
import android.text.Spannable
import android.text.Spanned
import android.text.method.TransformationMethod
import android.text.style.URLSpan
import android.util.Patterns
import android.view.View
import android.widget.TextView

class LinkTransformationMethod(private val activity: Activity) : TransformationMethod {

    override fun getTransformation(source: CharSequence, view: View): CharSequence {
        if (view is TextView) {
            if (view.text == null || view.text !is Spannable) {
                return source
            }
            val text = view.text as Spannable
            val spans = text.getSpans(0, view.length(), URLSpan::class.java)
            for (i in spans.indices.reversed()) {
                val oldSpan = spans[i]
                val start = text.getSpanStart(oldSpan)
                val end = text.getSpanEnd(oldSpan)
                val url = oldSpan.url
                if (!Patterns.WEB_URL.matcher(url).matches()) {
                    continue
                }
                text.removeSpan(oldSpan)
                text.setSpan(CustomTabsURLSpan(activity, url), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            return text
        }
        return source
    }

    override fun onFocusChanged(
            view: View,
            sourceText: CharSequence,
            focused: Boolean,
            direction: Int,
            previouslyFocusedRect: Rect
    ) {
        // DO NOTHING
    }
}