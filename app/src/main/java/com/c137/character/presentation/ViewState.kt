package com.c137.character.presentation

sealed class ViewState(val name: String, val enabled: Boolean) {

    object Idle : ViewState("idle", true)
    object Loading : ViewState("loading", false)
}