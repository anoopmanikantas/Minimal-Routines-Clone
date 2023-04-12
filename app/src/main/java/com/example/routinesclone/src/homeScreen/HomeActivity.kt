package com.example.routinesclone.src.homeScreen

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.routinesclone.src.actionsScreen.ActionsActivity
import com.example.routinesclone.src.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.src.repository.Repository
import com.example.routinesclone.utils.CompatActivity
import com.example.routinesclone.utils.Strings
import java.util.Calendar

enum class State {
    HOME_SCREEN,
    ON_ADD_ACTION_TAPPED,
    ACTIONS_SCREEN;
}

class HomeActivity : CompatActivity(), HomeScreenConstraintLayoutDelegate {
    private val homeScreenConstraintLayout by lazy { HomeScreenConstraintLayout(this) }
    private val repository by lazy { Repository(this) }
    private var state: State? = null
    private var actionsActivityIntent: Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleState(State.HOME_SCREEN)
    }

    private fun handleState(state: State?) {
        this.state = state
        when (state) {
            State.HOME_SCREEN -> {
                homeScreenConstraintLayout.delegate = this
                homeScreenConstraintLayout.listData =
                    repository.getActions(repository.selectedActions)
                setContentView(homeScreenConstraintLayout)
            }
            State.ON_ADD_ACTION_TAPPED -> {
                actionsActivityIntent = Intent(this, ActionsActivity(repository)::class.java)
                startActivity(actionsActivityIntent)
            }
            State.ACTIONS_SCREEN -> {}
            else -> throw Exception("Illegal State")
        }
    }

    // HomeScreen Delegate

    override fun timePickerTapped(context: Context, isStartDate: Boolean) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                val amPm = if (selectedHour < 12) "AM" else "PM"
                val finalHour = if (selectedHour > 12) selectedHour - 12 else selectedHour
                val newTime = "$finalHour:$selectedMinute:$amPm"
                if (isStartDate) {
                    homeScreenConstraintLayout.selectedStartTime = newTime
                } else {
                    homeScreenConstraintLayout.selectedEndTime = newTime
                }
                Toast.makeText(context, "SelectedTime: $selectedHour", Toast.LENGTH_SHORT).show()
            },
            hour,
            minute,
            false
        )
        timePickerDialog.show()
    }

    override fun activateActionsSwitchTapped() {
        TODO("Not yet implemented")
    }

    override fun onListItemTap(data: ActionsTileData) {
        when (data.title) {
            Strings.add_actions -> {
                handleState(State.ON_ADD_ACTION_TAPPED)
            }
            else -> throw Exception("Unknown action")
        }
    }
}