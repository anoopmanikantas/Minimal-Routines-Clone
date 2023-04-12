package com.example.routinesclone.utils

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.routinesclone.R
import com.example.routinesclone.sw.SWFont
import com.example.routinesclone.sw.SWFontWeight
import com.google.android.material.textview.MaterialTextView

open class CompatActivity: AppCompatActivity() {
    private val appBarTextView by lazy { MaterialTextView(this) }


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
        appBarTextView.text = getString(R.string.app_name)
        appBarTextView.setTextColor(getColor(R.color.text_primary))
        appBarTextView.typeface = SWFontWeight.BOLD.value
        appBarTextView.textSize = SWFont.HEADING1.value

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.customView = appBarTextView
    }

    private fun updateStatusBarColor() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getColor(R.color.dark_background)
    }
}