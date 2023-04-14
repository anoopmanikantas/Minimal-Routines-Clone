package com.example.routinesclone.main.actionsScreen.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.routinesclone.main.commonViews.ActionsListTile
import com.example.routinesclone.sw.SWColors
import com.example.routinesclone.sw.SWTextView
import com.example.routinesclone.utils.Strings
import com.example.routinesclone.utils.dp
import com.google.android.material.card.MaterialCardView

interface BottomSheetContentViewDelegate {
    fun onTriggerTapped()
    fun offTriggerTapped()
    fun noneTriggerTapped()
}

class BottomSheetContentView(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : MaterialCardView(context, attributeSet, defStyle) {
    private val constraintLayout: ConstraintLayout by lazy { ConstraintLayout(context) }
    private val constraintSet: ConstraintSet by lazy { ConstraintSet() }
    private val textView by lazy {SWTextView(context, text = Strings.trigger.value(context))}
    private val onTile by lazy { ActionsListTile(context) }
    private val offTile by lazy { ActionsListTile(context) }
    private val noneTile by lazy { ActionsListTile(context) }

    var delegate: BottomSheetContentViewDelegate? = null

    init {
        constraintLayout.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        addView(constraintLayout)
        constraintSet.clone(constraintLayout)
        setupViews()
        constraintSet.applyTo(constraintLayout)
    }

    private fun setupViews() {
        setBackgroundColor(SWColors.dark_background.value(context))

        setupTextView()
        setupOnTile()
        setupOffTile()
        setupNoneTile()
    }

    private fun setupTextView() {
        textView.apply {
            id = generateViewId()
            constraintLayout.addView(this)
            constraintSet.apply {
                constrainWidth(id, ConstraintSet.WRAP_CONTENT)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                connect(
                    id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START,
                    16.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP,
                    20.dp(context)
                )
            }
        }
    }

    private fun setupOnTile() {
        onTile.apply {
            id = generateViewId()
            titleText = Strings.on_title.value(context)
            setOnClickListener {
                delegate?.onTriggerTapped()
            }
            constraintLayout.addView(this)
            constraintSet.apply {
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                connect(
                    id,
                    ConstraintSet.TOP,
                    textView.id,
                    ConstraintSet.BOTTOM,
                    8.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START,
                    16.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END,
                    16.dp(context)
                )
            }
        }
    }

    private fun setupOffTile() {
        offTile.apply {
            id = generateViewId()
            titleText = Strings.off_title.value(context)
            setOnClickListener {
                delegate?.offTriggerTapped()
            }
            constraintLayout.addView(this)
            constraintSet.apply {
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                connect(
                    id,
                    ConstraintSet.TOP,
                    onTile.id,
                    ConstraintSet.BOTTOM,
                    8.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START,
                    16.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END,
                    16.dp(context)
                )
            }
        }
    }

    private fun setupNoneTile() {
        noneTile.apply {
            id = generateViewId()
            titleText = Strings.none_title.value(context)
            setOnClickListener {
                delegate?.noneTriggerTapped()
            }
            constraintLayout.addView(this)
            constraintSet.apply {
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                connect(
                    id,
                    ConstraintSet.TOP,
                    offTile.id,
                    ConstraintSet.BOTTOM,
                    8.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START,
                    16.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END,
                    16.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM,
                    10.dp(context)
                )
            }
        }
    }
}