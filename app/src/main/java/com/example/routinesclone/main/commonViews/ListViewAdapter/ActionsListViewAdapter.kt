package com.example.routinesclone.main.commonViews

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.routinesclone.main.commonViews.ListViewAdapter.ActionsTileData

class ActionsListViewAdapter(
    private val context: Context,
    private val arrayList: ArrayList<ActionsTileData>,
    listViewId: Int
) : ArrayAdapter<ActionsTileData>(context, listViewId, arrayList) {
    var isSubtitleListTile: Boolean = false

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listTile: ActionsListTile
        if (isSubtitleListTile) {
            listTile = SubtitleActionsListTile(context)
            listTile.subtitleText = arrayList[position].subtitle.value(context)
        } else {
            listTile = ActionsListTile(context)
        }
        listTile.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        listTile.id = View.generateViewId()
        listTile.image = arrayList[position].leadingImage
        listTile.titleText = arrayList[position].title.value(context)
        return if (isSubtitleListTile) (listTile as SubtitleActionsListTile) else listTile
    }
}