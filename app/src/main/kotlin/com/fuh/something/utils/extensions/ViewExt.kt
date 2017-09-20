package com.fuh.something.utils.extensions

import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.EditText

/**
 * Created by nick on 15.09.17.
 */
val View.ctx
    get() = context

var EditText.textValue: String
    get() = text.toString()
    set(v) {
        setText(v)
    }

var TextInputLayout.textValue: String
    get() = editText!!.textValue
    set(v) {
        editText!!.textValue = v
    }