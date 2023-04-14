package com.example.routinesclone.main.actionsScreen

import android.os.Build
import android.os.Bundle
import com.example.routinesclone.main.actionsScreen.viewmodel.ActionState
import com.example.routinesclone.main.actionsScreen.views.BottomSheetContentViewDelegate
import com.example.routinesclone.main.commonViews.ListViewAdapter.ActionsTileData
import com.example.routinesclone.main.repository.Repository
import com.example.routinesclone.utils.CompatActivity
import com.example.routinesclone.utils.Strings
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private enum class ActionsActivityState {
    ACTIONS_SCREEN,
    TAPPED_BLUETOOTH,
    TAPPED_MOBILE_DATA,
    TAPPED_LOCATION,
    SELECTED_TRIGGER;
}

class ActionsActivity : CompatActivity(), ActionsScreenConstraintLayoutDelegate, BottomSheetContentViewDelegate {
    private val actionsScreenConstraintLayout by lazy { ActionsScreenConstraintLayout(this) }

    private var repository: Repository? = null
    private var actionsState: Map<Strings, ActionState>? = null
    private var state: ActionsActivityState = ActionsActivityState.ACTIONS_SCREEN

    private var bottomSheetFragment: BottomSheetDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appBarTitle = Strings.actions_title.value(this)
        repository = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable(Repository.RepositoryName, Repository::class.java)
        } else {
            Repository()
        }
        repository?.context = this
        actionsState = repository?.actionState
        state = ActionsActivityState.ACTIONS_SCREEN
        handleState(state)
    }

    private fun handleState(state: ActionsActivityState) {
        when (state) {
            ActionsActivityState.ACTIONS_SCREEN -> {
                actionsScreenConstraintLayout.delegate = this
                actionsScreenConstraintLayout.bottomSheetContentViewDelegate = this
                actionsScreenConstraintLayout.listData = getListData()
                setContentView(actionsScreenConstraintLayout)
                actionsState = repository?.actionState
            }
            else -> throw Exception("Illegal State")
        }
    }

    private fun getListData(): ArrayList<ActionsTileData> {
        val actionsListData: ArrayList<ActionsTileData> = ArrayList()
        repository?.getAllActions()?.forEach {
            it.subtitle = actionsState?.get(it.title)?.description!!
            actionsListData.add(it)
        }
        return actionsListData
    }

    override fun onListItemTap(data: ActionsTileData, bottomSheetFragment: BottomSheetDialogFragment) {
        this.bottomSheetFragment = bottomSheetFragment
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        state = when (data.title) {
            Strings.bluetooth -> ActionsActivityState.TAPPED_BLUETOOTH
            Strings.mobile_data -> ActionsActivityState.TAPPED_MOBILE_DATA
            Strings.location -> ActionsActivityState.TAPPED_LOCATION
            else -> throw Exception("Unknown tile with title${data.title}")
        }
    }

    override fun onTriggerTapped() {
        bottomSheetFragment?.dismiss()
    }

    override fun offTriggerTapped() {
        bottomSheetFragment?.dismiss()
    }

    override fun noneTriggerTapped() {
        bottomSheetFragment?.dismiss()
    }
}
