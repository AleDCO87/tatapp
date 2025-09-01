package com.example.tatapp.data.prefs

package com.example.tatapp.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val SETTINGS_NAME = "settings_pref"
val Context.dataStore by preferencesDataStore(SETTINGS_NAME)

object PrefKeys {
    val DARK_MODE = booleanPreferencesKey("dark_mode")
}

class SettingsRepository(private val context: Context) {
    val darkMode: Flow<Boolean> = context.dataStore.data.map { it[PrefKeys.DARK_MODE] ?: false }
    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { it[PrefKeys.DARK_MODE] = enabled }
    }
}
