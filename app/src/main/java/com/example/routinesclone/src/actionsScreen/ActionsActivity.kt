package com.example.routinesclone.src.actionsScreen

import android.os.Bundle
import com.example.routinesclone.src.homeScreen.HomeScreenConstraintLayout
import com.example.routinesclone.src.repository.Repository
import com.example.routinesclone.utils.CompatActivity

class ActionsActivity(val repository: Repository): CompatActivity() {
    private val homeScreenConstraintLayout by lazy { HomeScreenConstraintLayout(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeScreenConstraintLayout)
    }
}