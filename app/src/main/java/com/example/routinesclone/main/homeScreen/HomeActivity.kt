package com.example.routinesclone.main.homeScreen

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.routinesclone.main.actionsScreen.ActionsActivity
import com.example.routinesclone.main.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.main.repository.Repository
import com.example.routinesclone.utils.CompatActivity
import com.example.routinesclone.utils.Strings
import java.util.Calendar

enum class HomeActivityState {
    HOME_SCREEN,
    ON_ADD_ACTION_TAPPED,
    ACTIONS_SCREEN;
}

class HomeActivity : CompatActivity(), HomeScreenConstraintLayoutDelegate {
    private val homeScreenConstraintLayout by lazy { HomeScreenConstraintLayout(this) }
    private val repository by lazy { Repository() }
    private var state: HomeActivityState? = null
    private var actionsActivityIntent: Intent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appBarTitle = Strings.app_name.value(this)
        repository.context = this
        handleState(HomeActivityState.HOME_SCREEN)
    }

    private fun handleState(state: HomeActivityState?) {
        this.state = state
        when (state) {
            HomeActivityState.HOME_SCREEN -> {
                homeScreenConstraintLayout.delegate = this
                homeScreenConstraintLayout.listData =
                    repository.getActions(repository.selectedActions)
                setContentView(homeScreenConstraintLayout)
            }
            HomeActivityState.ON_ADD_ACTION_TAPPED -> {
                actionsActivityIntent = Intent(this, ActionsActivity::class.java)
                actionsActivityIntent?.putExtra(Repository.RepositoryName, repository)
                startActivity(actionsActivityIntent)
            }
            HomeActivityState.ACTIONS_SCREEN -> {}
            else -> throw Exception("Illegal State")
        }
    }

    //<editor-fold desc="Home Screen Delegate">
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
                handleState(HomeActivityState.ON_ADD_ACTION_TAPPED)
            }
            else -> throw Exception("Unknown action")
        }
    }
    //</editor-fold>
}
