package com.example.tatapp.ui.screens.config

import android.widget.Switch
import androidx.compose.material3.Scaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen(settingsVm: SettingsViewModel) {
    val dark by settingsVm.darkMode.collectAsState()
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Configuración") }) }
    ) { padding ->
        Row(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tema oscuro", modifier = Modifier.weight(1f), fontSize = 18.sp)
            Switch(checked = dark, onCheckedChange = { settingsVm.toggleDark() })
        }
    }
}
