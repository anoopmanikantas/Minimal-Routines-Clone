package com.example.routinesclone.utils

import android.content.Context
import android.util.TypedValue

fun Float.dp(context: Context): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    this.toFloat(),
    context.resources.displayMetrics
).toFloat()