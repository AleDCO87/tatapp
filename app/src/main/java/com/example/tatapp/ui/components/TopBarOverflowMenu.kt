package com.example.tatapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun TopBarOverflowMenu(
    isDark: Boolean,
    onToggleDark: () -> Unit,
    onOpenPerfil: () -> Unit,
    onOpenConfig: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = Modifier.semantics { contentDescription = "Más opciones" }
    ) { Icon(Icons.Default.MoreVert, contentDescription = null) }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

        DropdownMenuItem(
            text = {
                Row {
                    Text("Modo oscuro", modifier = Modifier.weight(1f))
                    Switch(checked = isDark, onCheckedChange = {
                        onToggleDark()
                    })
                }
            },
            onClick = { /* Switch maneja el click */ }
        )

        DropdownMenuItem(text = { Text("Perfil") }, onClick = {
            expanded = false; onOpenPerfil()
        })
        DropdownMenuItem(text = { Text("Configuración") }, onClick = {
            expanded = false; onOpenConfig()
        })
    }
}


