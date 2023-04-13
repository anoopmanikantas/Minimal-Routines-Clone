package com.example.routinesclone.utils

import android.annotation.SuppressLint
import android.content.Context

enum class Strings {
    app_name,
    select_start_date,
    select_end_date,
    actions_title,
    actions_description,
    add_actions,
    bluetooth,
    mobile_data,
    location,
    empty;

    @SuppressLint("DiscouragedApi")
    public fun value(context: Context): String {
        val res = context.resources;
        val resId = res.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) {
            res.getString(resId)
        } else {
            name
        }
    }
}