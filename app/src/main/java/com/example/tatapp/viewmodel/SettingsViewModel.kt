package com.example.tatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tatapp.data.prefs.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(private val repo: SettingsRepository) : ViewModel() {
    val darkMode: StateFlow<Boolean> =
        repo.darkMode.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    fun toggleDark() = viewModelScope.launch {
        repo.setDarkMode(!darkMode.value)
    }
}
