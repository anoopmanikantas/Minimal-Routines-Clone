package com.example.routinesclone.main.actionsScreen.views

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.properties.Delegates

class TriggerBottomSheetDialogFragment: BottomSheetDialogFragment() {
    var delegate: BottomSheetContentViewDelegate? by Delegates.observable(null) {_, _, delegate ->
        bottomSheetContentView?.delegate = delegate
    }
    private var bottomSheetContentView: BottomSheetContentView? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext())
        val view = BottomSheetContentView(requireContext())
        bottomSheetContentView = view
        view.delegate = delegate
        dialog.setContentView(view)
        return dialog
    }
}