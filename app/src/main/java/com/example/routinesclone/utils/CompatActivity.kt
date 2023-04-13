package com.example.routinesclone.utils

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.routinesclone.R
import com.example.routinesclone.sw.SWColors
import com.example.routinesclone.sw.SWFont
import com.example.routinesclone.sw.SWFontWeight
import com.example.routinesclone.sw.SWTextView
import kotlin.properties.Delegates

open class CompatActivity: AppCompatActivity() {
    private val appBarTextView by lazy { SWTextView(this) }
    var appBarTitle: String? by Delegates.observable(null) {_, _, newValue ->
        appBarTextView.text = newValue
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAppBar()
    }

    private fun setupAppBar() {
        updateStatusBarColor()
        updateActionBar()
        updateActionBarTitleView()
    }

    // Action Bar setup

    private fun updateActionBar() {
        val colorDrawable = ColorDrawable(getColor(R.color.dark_background))
        supportActionBar?.setBackgroundDrawable(colorDrawable)

        val elevation = 0 * resources.displayMetrics.density
        supportActionBar?.elevation = elevation
    }

    private fun updateActionBarTitleView() {
        appBarTextView.apply {
            textColor = SWColors.text_primary
            fontSize = SWFont.HEADING1
            fontWeight = SWFontWeight.BOLD
        }
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowCustomEnabled(true)
            customView = appBarTextView
        }
    }

    private fun updateStatusBarColor() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getColor(R.color.dark_background)
    }
}