package com.example.routinesclone.src.repository

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.routinesclone.R
import com.example.routinesclone.src.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.utils.Strings

class Repository(val context: Context) {
    // TODO: Get the data from database
    var selectedActions: Map<Strings, Boolean> = mutableMapOf(
        Strings.bluetooth to false,
        Strings.mobileData to false,
        Strings.location to false
    )

    fun getAllActions(): ArrayList<ActionsTileData> {
        val arrList: ArrayList<ActionsTileData> = ArrayList()
        selectedActions.forEach {
            arrList.add(
                ActionsTileData(
                    title = it.key,
                    subtitle = "",
                    getDrawableForAction(it.key)
                )
            )
        }
        return arrList
    }

    // TODO: Update the Drawable Files
    fun getActions(selectedActions: Map<Strings, Boolean>): ArrayList<ActionsTileData> {
        val arrList: ArrayList<ActionsTileData> = ArrayList()
        selectedActions.forEach {
            if (it.value) {
                arrList.add(
                    ActionsTileData(
                        title = it.key,
                        subtitle = "",
                        getDrawableForAction(it.key)
                    )
                )
            }
        }
        arrList.add(
            ActionsTileData(
                title = Strings.add_actions,
                subtitle = "",
                leadingImage = getDrawableForAction(Strings.add_actions)
            )
        )
        return arrList
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getDrawableForAction(strings: Strings): Drawable? {
        return when (strings) {
            Strings.bluetooth -> context.resources.getDrawable(
                R.drawable.bluetooth,
                null
            )
            Strings.location -> context.resources.getDrawable(
                R.drawable.location,
                null
            )
            Strings.mobileData -> context.resources.getDrawable(
                R.drawable.mobiledata,
                null
            )
            Strings.add_actions -> context.resources.getDrawable(
                R.drawable.add,
                null
            )
            else -> throw Exception("Unknown string")
        }
    }
}