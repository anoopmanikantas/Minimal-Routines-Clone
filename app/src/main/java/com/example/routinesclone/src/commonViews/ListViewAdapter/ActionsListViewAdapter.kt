package com.example.routinesclone.src.commonViews

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.routinesclone.src.commonViews.ListViewAdapter.ActionsTileData

class ActionsListViewAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ActionsTileData>,
    listViewId: Int
) : ArrayAdapter<ActionsTileData>(context, listViewId, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listTile = ActionsListTile(context)
        listTile.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        listTile.id = View.generateViewId()
        listTile.image = arrayList[position].leadingImage
        listTile.leadingText = arrayList[position].title.value(context)
        return listTile
    }
}