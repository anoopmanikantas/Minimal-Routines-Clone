package com.example.routinesclone.sw

import android.graphics.Typeface

enum class SWFont(val value: Float) {
    HEADING1(20f),
    HEADING2(16f),
    HEADING3(14f),
    HEADING4(12f),
    BODY(10f);

    companion object {
        var fontWeight: SWFontWeight = SWFontWeight.BOLD
    }
}

enum class SWFontWeight(val value: Typeface) {
    BOLD(Typeface.create(null, 700, false)),
    MEDIUM(Typeface.create(null, 500, false)),
    REGULAR(Typeface.create(null, 400, false))
}