package com.example.routinesclone.src.homeScreen

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.routinesclone.R
import com.example.routinesclone.src.commonViews.ActionsListViewAdapter
import com.example.routinesclone.src.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.sw.SWColors
import com.example.routinesclone.sw.SWFont
import com.example.routinesclone.sw.SWFontWeight
import com.example.routinesclone.sw.SWTextView
import com.example.routinesclone.utils.dp
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.properties.Delegates

interface HomeScreenConstraintLayoutDelegate {
    fun timePickerTapped(context: Context, isStartDate: Boolean)
    fun activateActionsSwitchTapped()
    fun onListItemTap(data: ActionsTileData)
}

class HomeScreenConstraintLayout(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attr, defStyle) {
    var delegate: HomeScreenConstraintLayoutDelegate? = null
    var listData: ArrayList<ActionsTileData> by Delegates.observable(
        ArrayList()
    ) { _, _, newValue ->
        actionsListView.adapter =
            ActionsListViewAdapter(context, newValue, actionsListView.id)
    }
    var selectedStartTime: String? by Delegates.observable(null) { _, _, newTime ->
        selectedStartTimeTextView.text = newTime
    }
    var selectedEndTime: String? by Delegates.observable(null) { _, _, newTime ->
        selectedEndTimeTextView.text = newTime
    }

    private val selectStartTimeTextView by lazy {
        SWTextView(
            context,
            context.resources.getText(R.string.select_start_date).toString()
        )
    }

    private val selectEndTimeTextView by lazy {
        SWTextView(
            context,
            context.resources.getText(R.string.select_end_date).toString()
        )
    }
    private val selectedStartTimeTextView by lazy { SWTextView(context) }
    private val selectedEndTimeTextView by lazy { SWTextView(context) }
    private val actionTitleTextView by lazy {
        SWTextView(
            context,
            context.resources.getText(R.string.actions_title).toString()
        )
    }
    private val activateActionsSwitch by lazy { SwitchMaterial(context) }
    private val actionsDescriptionTextView by lazy {
        SWTextView(
            context,
            resources.getString(R.string.actions_description)
        )
    }
    private val actionsListView by lazy { ListView(context) }

    private val constraintSet: ConstraintSet = ConstraintSet()

    init {
        id = generateViewId()
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        constraintSet.clone(this)
        setupViews()
        constraintSet.applyTo(this)
    }

    private fun setupViews() {
        setBackgroundColor(SWColors.dark_background.value(context))
        setupSelectStartTimeTextView()
        setupSelectedStartTimeTextView()
        setupSelectEndTimeTextView()
        setupSelectedEndTimeTextView()
        setupActionTitleTextView()
        setupActivateActionsSwitch()
        setupActionDescriptionTextView()
        setupActionsListView()
    }

    private fun setupSelectedEndTimeTextView() {
        selectedEndTimeTextView.apply {
            id = generateViewId()
            textColor = SWColors.text_secondary
            fontSize = SWFont.HEADING2
            fontWeight = SWFontWeight.BOLD
        }
        addView(selectedEndTimeTextView)
        constraintSet.apply {
            constrainWidth(selectedEndTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            constrainHeight(selectedEndTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            connect(
                selectedEndTimeTextView.id,
                ConstraintSet.TOP,
                selectStartTimeTextView.id,
                ConstraintSet.BOTTOM,
                20.dp(context)
            )
            connect(
                selectedEndTimeTextView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                16.dp(context)
            )
        }
    }

    private fun setupSelectedStartTimeTextView() {
        selectedStartTimeTextView.apply {
            id = generateViewId()
            textColor = SWColors.text_secondary
            fontSize = SWFont.HEADING2
            fontWeight = SWFontWeight.BOLD
        }
        addView(selectedStartTimeTextView)
        constraintSet.apply {
            constrainWidth(selectedStartTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            constrainHeight(selectedStartTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            connect(
                selectedStartTimeTextView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                20.dp(context)
            )
            connect(
                selectedStartTimeTextView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                16.dp(context)
            )
        }
    }

    private fun setupSelectStartTimeTextView() {
        selectStartTimeTextView.apply {
            id = generateViewId()
            fontSize = SWFont.HEADING2
            fontWeight = SWFontWeight.BOLD
            setOnClickListener {
                delegate?.timePickerTapped(context, true)
            }
        }

        addView(selectStartTimeTextView)
        constraintSet.apply {
            constrainWidth(selectStartTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            constrainHeight(selectStartTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            connect(
                selectStartTimeTextView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                20.dp(context)
            )
            connect(
                selectStartTimeTextView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                16.dp(context)
            )
        }
    }

    private fun setupSelectEndTimeTextView() {
        selectEndTimeTextView.apply {
            id = generateViewId()
            fontSize = SWFont.HEADING2
            fontWeight = SWFontWeight.BOLD
            setOnClickListener {
                delegate?.timePickerTapped(context, false)
            }
        }

        addView(selectEndTimeTextView)
        constraintSet.apply {
            constrainWidth(selectEndTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            constrainHeight(selectEndTimeTextView.id, ConstraintSet.WRAP_CONTENT)
            connect(
                selectEndTimeTextView.id,
                ConstraintSet.TOP,
                selectStartTimeTextView.id,
                ConstraintSet.BOTTOM,
                20.dp(context)
            )
            connect(
                selectEndTimeTextView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                16.dp(context)
            )
        }
    }

    private fun setupActionTitleTextView() {
        actionTitleTextView.apply {
            id = generateViewId()
            fontWeight = SWFontWeight.BOLD
        }

        addView(actionTitleTextView)
        constraintSet.apply {
            constrainWidth(actionTitleTextView.id, ConstraintSet.WRAP_CONTENT)
            constrainHeight(actionTitleTextView.id, ConstraintSet.WRAP_CONTENT)
            connect(
                actionTitleTextView.id,
                ConstraintSet.TOP,
                selectEndTimeTextView.id,
                ConstraintSet.BOTTOM,
                20.dp(context)
            )
            connect(
                actionTitleTextView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                16.dp(context)
            )
        }
    }

    private fun setupActivateActionsSwitch() {
        activateActionsSwitch.apply {
            id = generateViewId()
            trackTintList = ColorStateList.valueOf(SWColors.text_tertiary.value(context))
            thumbTintList = ColorStateList.valueOf(SWColors.text_primary.value(context))
            setOnCheckedChangeListener { _, isChecked ->
                onActionsSwitchTap(isChecked)
            }
        }
        addView(activateActionsSwitch)
        constraintSet.apply {
            constrainWidth(activateActionsSwitch.id, ConstraintSet.WRAP_CONTENT)
            connect(
                activateActionsSwitch.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                20.dp(context)
            )
            connect(
                activateActionsSwitch.id,
                ConstraintSet.TOP,
                selectedEndTimeTextView.id,
                ConstraintSet.BOTTOM,
                20.dp(context)
            )
        }
    }

    private fun setupActionDescriptionTextView() {
        actionsDescriptionTextView.apply {
            id = generateViewId()
            fontSize = SWFont.HEADING3
            fontWeight = SWFontWeight.REGULAR
            textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        }
        addView(actionsDescriptionTextView)
        constraintSet.apply {

            constrainHeight(actionsDescriptionTextView.id, ConstraintSet.WRAP_CONTENT)
            connect(
                actionsDescriptionTextView.id,
                ConstraintSet.TOP,
                actionTitleTextView.id,
                ConstraintSet.BOTTOM,
                16.dp(context)
            )
            connect(
                actionsDescriptionTextView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                16.dp(context)
            )
            connect(
                actionsDescriptionTextView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END,
                16.dp(context)
            )
        }
    }

    private fun setupActionsListView() {
        actionsListView.apply {
            id = generateViewId()
            adapter = ActionsListViewAdapter(context, listData, id)
            setOnItemClickListener { parent, _, i, _ ->
                val data = parent.getItemAtPosition(i) as ActionsTileData
                delegate?.onListItemTap(data)
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
                actionsDescriptionTextView.id,
                ConstraintSet.BOTTOM,
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

    private fun onActionsSwitchTap(isChecked: Boolean) {
        activateActionsSwitch.apply {
            trackTintList = if (isChecked) ColorStateList.valueOf(
                SWColors.secondary.value(context)
            ) else ColorStateList.valueOf(SWColors.text_tertiary.value(context))
            thumbTintList = if (isChecked) ColorStateList.valueOf(
                SWColors.primary.value(context)
            ) else ColorStateList.valueOf(SWColors.text_primary.value(context))
        }
        delegate?.activateActionsSwitchTapped()
    }
}
