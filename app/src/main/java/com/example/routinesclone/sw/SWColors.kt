package com.example.routinesclone.sw

import android.content.Context
import com.example.routinesclone.R

enum class SWColors(private val value: Int) {
    primary(R.color.primary),
    secondary(R.color.secondary),
    dark_background(R.color.dark_background),
    text_primary(R.color.text_primary),
    text_secondary(R.color.text_secondary),
    text_tertiary(R.color.text_tertiary);

    public fun value(context: Context): Int =
        context.resources.getColor(this.value, null)

}