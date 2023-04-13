package com.example.routinesclone.main.actionsScreen

import android.content.Context
import android.os.Bundle
import com.example.routinesclone.main.actionsScreen.viewmodel.ActionState
import com.example.routinesclone.main.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.main.repository.Repository
import com.example.routinesclone.utils.CompatActivity
import com.example.routinesclone.utils.Strings

private enum class ActionsActivityState {
    ACTIONS_SCREEN,
    TAPPED_BLUETOOTH,
    TAPPED_MOBILE_DATA,
    TAPPED_LOCATION,
    SELECTED_TRIGGER;
}

class ActionsActivity : CompatActivity(), ActionsScreenConstraintLayoutDelegate {
    private val actionsScreenConstraintLayout by lazy { ActionsScreenConstraintLayout(this) }

    private var repository: Repository? = null
    private var actionsState: Map<Strings, ActionState>? = null
    private var state: ActionsActivityState = ActionsActivityState.ACTIONS_SCREEN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appBarTitle = Strings.actions_title.value(this)
        handleState(ActionsActivityState.ACTIONS_SCREEN)
    }

    private fun handleState(state: ActionsActivityState) {
        this.state = state
        when (state) {
            ActionsActivityState.ACTIONS_SCREEN -> {
                actionsScreenConstraintLayout.delegate = this
                repository = intent.extras?.getParcelable(Repository.RepositoryName, Repository::class.java)
                repository?.context = this
                actionsScreenConstraintLayout.listData = repository?.getAllActions()!!
                setContentView(actionsScreenConstraintLayout)
                actionsState = repository?.actionState
            }
            else -> throw Exception("Illegal State")
        }
    }

    override fun onListItemTap(data: ActionsTileData) {
        TODO("Not yet implemented")
    }

    override fun onBluetoothActionTap(context: Context) {
        TODO("Not yet implemented")
    }

    override fun onMobileDataActionTap(context: Context) {
        TODO("Not yet implemented")
    }

    override fun onLocationServicesActionTap(context: Context) {
        TODO("Not yet implemented")
    }
}
