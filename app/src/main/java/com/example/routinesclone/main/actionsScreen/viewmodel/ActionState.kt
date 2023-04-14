package com.example.routinesclone.main.actionsScreen.viewmodel

import com.example.routinesclone.utils.Strings

enum class ActionState(val title: Strings, val description: Strings) {
    ON(Strings.on_title, Strings.on_description),
    OFF(Strings.off_title, Strings.off_description),
    NONE(Strings.none_title, Strings.none_description);
}