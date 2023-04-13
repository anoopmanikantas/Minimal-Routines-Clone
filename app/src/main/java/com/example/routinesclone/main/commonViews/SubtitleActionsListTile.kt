package com.example.routinesclone.main.commonViews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import com.example.routinesclone.sw.SWFont
import com.example.routinesclone.sw.SWFontWeight
import com.example.routinesclone.utils.dp
import kotlin.properties.Delegates

class SubtitleActionsListTile(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ActionsListTile(context, attributeSet, defStyle) {

    var subtitleText: String? by Delegates.observable(null) { _, _, text ->
        subtitleTextView.text = text
    }

    override fun setupTrailingViews() {
        setupTitleView()
        setupSubtitleView()
    }

    private fun setupTitleView() {
        titleTextView.apply {
            id = generateViewId()
            fontSize = SWFont.HEADING4
            fontWeight = SWFontWeight.MEDIUM
            textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            constraintLayout.addView(this)
            constraintSet.apply {
                constrainWidth(id, ConstraintSet.WRAP_CONTENT)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                connect(
                    id,
                    ConstraintSet.START,
                    imageView.id,
                    ConstraintSet.END,
                    8.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP,
                    14.dp(context)
                )
            }
        }
    }

    private fun setupSubtitleView() {
        subtitleTextView.apply {
            id = generateViewId()
            fontSize = SWFont.BODY
            fontWeight = SWFontWeight.REGULAR
            textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            constraintLayout.addView(this)
            constraintSet.apply {
                constrainWidth(id, ConstraintSet.WRAP_CONTENT)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                connect(
                    id,
                    ConstraintSet.START,
                    imageView.id,
                    ConstraintSet.END,
                    8.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM,
                    14.dp(context)
                )
            }
        }
    }
}