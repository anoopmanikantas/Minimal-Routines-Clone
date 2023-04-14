package com.example.routinesclone.main.actionsScreen

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.routinesclone.main.actionsScreen.views.BottomSheetContentViewDelegate
import com.example.routinesclone.main.actionsScreen.views.TriggerBottomSheetDialogFragment
import com.example.routinesclone.main.commonViews.ActionsListViewAdapter
import com.example.routinesclone.main.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.sw.SWColors
import com.example.routinesclone.utils.dp
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.properties.Delegates

interface ActionsScreenConstraintLayoutDelegate {
    fun onListItemTap(data: ActionsTileData, bottomSheetFragment: BottomSheetDialogFragment)
}

class ActionsScreenConstraintLayout(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attr, defStyle) {
    //<editor-fold desc="Public Variables">
    var delegate: ActionsScreenConstraintLayoutDelegate? = null
    var bottomSheetContentViewDelegate: BottomSheetContentViewDelegate? by Delegates.observable(null) {_, _, delegate ->
        triggerBottomSheetDialogFragment.delegate = delegate
    }
    var listData: ArrayList<ActionsTileData> by Delegates.observable(
        ArrayList()
    ) { _, _, newValue ->
        actionsListView.adapter =
            ActionsListViewAdapter(context, newValue, actionsListView.id, true)
    }
    //</editor-fold>

    //<editor-fold desc="Private Views">
    private val actionsListView by lazy { ListView(context) }
    private val triggerBottomSheetDialogFragment by lazy {TriggerBottomSheetDialogFragment()}
    private val constraintSet = ConstraintSet()
    //</editor-fold>

    init {
        id = generateViewId()
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setBackgroundColor(SWColors.dark_background.value(context))
        constraintSet.clone(this)
        setupViews()
        constraintSet.applyTo(this)
    }

    //<editor-fold desc="Setup Views">

    private fun setupViews() {
        setupActionsListView()
    }

    private fun setupActionsListView() {
        actionsListView.apply {
            id = generateViewId()
            val adapter = ActionsListViewAdapter(context, listData, id, true)
            this.adapter = adapter
            setOnItemClickListener { parent, _, i, _ ->
                val data = parent.getItemAtPosition(i) as ActionsTileData
                delegate?.onListItemTap(data, triggerBottomSheetDialogFragment)
            }

            divider = ColorDrawable(SWColors.dark_background.value(context))
            dividerHeight = 8.dp(context)
        }

        addView(actionsListView)
        constraintSet.apply {
            connect(
                actionsListView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                16.dp(context)
            )
            connect(
                actionsListView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                20.dp(context)
            )
            connect(
                actionsListView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                16.dp(context)
            )
            connect(
                actionsListView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM,
                16.dp(context)
            )
        }
    }
    //</editor-fold>

}
