package com.example.routinesclone.sw

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.textview.MaterialTextView
import kotlin.properties.Delegates

class SWTextView(
    context: Context,
    text: String? = null,
    fontWeight: SWFontWeight = SWFontWeight.MEDIUM,
    fontSize: SWFont = SWFont.HEADING1,
    textColor: SWColors = SWColors.text_primary,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : MaterialTextView(context, attrs, defStyle) {
    var text: String? by Delegates.observable(text) {_, _, newValue ->
        setText(newValue)
    }

    var fontWeight: SWFontWeight by Delegates.observable(fontWeight) {_, _, newValue ->
        typeface = newValue.value
    }

    var fontSize: SWFont by Delegates.observable(fontSize) {_, _, newValue ->
        textSize = newValue.value
    }

    var textColor: SWColors by Delegates.observable(textColor) {_, _, newValue ->
        setTextColor(newValue.value(context))
    }

    init {
        setText(text)
        textSize = fontSize.value
        setTextColor(textColor.value(context))
        typeface = fontWeight.value
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
}