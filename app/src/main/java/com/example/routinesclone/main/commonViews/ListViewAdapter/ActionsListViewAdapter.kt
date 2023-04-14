package com.example.routinesclone.main.commonViews

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.routinesclone.main.commonViews.ListViewAdapter.ActionsTileData

class ActionsListViewAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ActionsTileData>,
    listViewId: Int,
    private val isSubtitleListTile: Boolean = false
) : ArrayAdapter<ActionsTileData>(context, listViewId, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (isSubtitleListTile) {
            val listTile = SubtitleActionsListTile(context)
            listTile.id = View.generateViewId()
            listTile.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            listTile.image = arrayList[position].leadingImage
            listTile.titleText = arrayList[position].title.value(context)
            listTile.subtitleText = arrayList[position].subtitle.value(context)
            Log.d("List Tile Log", "${listTile.subtitleText}")
            return listTile
        } else {
            val listTile = ActionsListTile(context)
            listTile.id = View.generateViewId()
            listTile.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            listTile.image = arrayList[position].leadingImage
            listTile.titleText = arrayList[position].title.value(context)
            return listTile
        }
    }
}