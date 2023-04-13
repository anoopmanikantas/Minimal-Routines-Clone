package com.example.routinesclone.main.repository

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import com.example.routinesclone.R
import com.example.routinesclone.main.actionsScreen.viewmodel.ActionState
import com.example.routinesclone.main.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.utils.Strings

class Repository() : Parcelable {

    var context: Context? = null

    // TODO: Get the data from database
    var selectedActions: Map<Strings, Boolean> = mutableMapOf(
        Strings.bluetooth to false,
        Strings.mobile_data to false,
        Strings.location to false
    )

    // TODO: GET the data from database
    var actionState: Map<Strings, ActionState> = mutableMapOf(
        Strings.bluetooth to ActionState.NONE,
        Strings.mobile_data to ActionState.NONE,
        Strings.location to ActionState.NONE
    )

    constructor(parcel: Parcel) : this() {

    }

    fun getAllActions(): ArrayList<ActionsTileData> {
        val arrList: ArrayList<ActionsTileData> = ArrayList()
        selectedActions.forEach {
            arrList.add(
                ActionsTileData(
                    title = it.key,
                    subtitle = Strings.empty,
                    getDrawableForAction(it.key)
                )
            )
        }
        return arrList
    }

    fun getActions(selectedActions: Map<Strings, Boolean>): ArrayList<ActionsTileData> {
        val arrList: ArrayList<ActionsTileData> = ArrayList()
        selectedActions.forEach {
            if (it.value) {
                arrList.add(
                    ActionsTileData(
                        title = it.key,
                        subtitle = Strings.empty,
                        getDrawableForAction(it.key)
                    )
                )
            }
        }
        arrList.add(
            ActionsTileData(
                title = Strings.add_actions,
                subtitle = Strings.empty,
                leadingImage = getDrawableForAction(Strings.add_actions)
            )
        )
        return arrList
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getDrawableForAction(strings: Strings): Drawable? {
        return when (strings) {
            Strings.bluetooth -> context?.resources?.getDrawable(
                R.drawable.bluetooth,
                null
            )
            Strings.location -> context?.resources?.getDrawable(
                R.drawable.location,
                null
            )
            Strings.mobile_data -> context?.resources?.getDrawable(
                R.drawable.mobiledata,
                null
            )
            Strings.add_actions -> context?.resources?.getDrawable(
                R.drawable.add,
                null
            )
            else -> throw Exception("Unknown string")
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        val RepositoryName = this.toString()

        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }
}