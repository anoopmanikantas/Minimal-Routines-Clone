package com.example.routinesclone.src.commonViews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import com.example.routinesclone.R
import com.example.routinesclone.sw.SWColors
import com.example.routinesclone.sw.SWFont
import com.example.routinesclone.sw.SWFontWeight
import com.example.routinesclone.sw.SWTextView
import com.example.routinesclone.utils.dp
import com.google.android.material.card.MaterialCardView
import kotlin.properties.Delegates

class ActionsListTile(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    MaterialCardView(context, attrs, defStyle) {

    var image: Drawable? by Delegates.observable(null) { _, _, image ->
        imageView.setImageDrawable(image)
    }

    var leadingText: String? by Delegates.observable(null) { _, _, text ->
        textView.text = text
    }

    private val imageView by lazy { ImageView(context) }
    private val textView by lazy { SWTextView(context) }

    private val constraintLayout: ConstraintLayout by lazy { ConstraintLayout(context) }
    private val constraintSet: ConstraintSet by lazy { ConstraintSet() }

    init {
        radius = 8f.dp(context)
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
        setPadding(0, 0, 0, 8.dp(context))
        setCardBackgroundColor(SWColors.primary.value(context))
        setupLeadingImageView()
        setupTitleView()
    }

    private fun setupTitleView() {
        textView.apply {
            id = generateViewId()
            fontSize = SWFont.HEADING4
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
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP,
                    16.dp(context)
                )
                connect(
                    id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM,
                    16.dp(context)
                )
            }
        }

    }

    private fun setupLeadingImageView() {
        imageView.apply {
            id = generateViewId()
            image = ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_foreground, null)
            imageTintList = ColorStateList.valueOf(SWColors.text_primary.value(context))
        }
        constraintLayout.addView(imageView)

        constraintSet.apply {
            connect(
                imageView.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                16.dp(context)
            )
            connect(
                imageView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START,
                16.dp(context)
            )
            connect(
                imageView.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM,
                16.dp(context)
            )
            constrainHeight(imageView.id, 24.dp(context))
            constrainWidth(imageView.id, 24.dp(context))
        }
    }
}