package com.example.shmrapp.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingItem(title: String, onClick: () -> Unit) {
    ListItem(
        content = {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
        },
        trail = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        },
        onClick = onClick
    )
}
