package com.example.routinesclone.utils

import android.content.Context
import android.util.TypedValue

fun Int.dp(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), metrics).toInt()
}