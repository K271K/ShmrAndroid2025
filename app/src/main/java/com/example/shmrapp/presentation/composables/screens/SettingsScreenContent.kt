package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shmrapp.R
import com.example.shmrapp.presentation.composables.MyListItemOnlyText

val settingsTitleMock = listOf(
    "Основной цвет",
    "Звуки",
    "Хаптики",
    "Код пароль",
    "Синхронизация",
    "Язык",
    "О программе"
)

@Preview(showBackground = true)
@Composable
fun SettingsScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var isDarkTheme by remember { mutableStateOf(false) }
        MyListItemOnlyText(
            modifier = Modifier
                .height(56.dp),
            content = {
                Text(text = "Светлая тёмная авто")
            },
            trailContent = {
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { isDarkTheme = !isDarkTheme },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(R.color.green_light),
                        checkedTrackColor = colorResource(R.color.green_dark),
                    )
                )
            }
        )
        HorizontalDivider()
        LazyColumn {
            items(settingsTitleMock) { title ->
                MyListItemOnlyText(
                    modifier = Modifier
                        .height(56.dp),
                    content = {
                        Text(text = title)
                    },
                    trailContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.settings_trailing_element),
                            contentDescription = "Настройки"
                        )
                    }
                )
                HorizontalDivider()
            }
        }
    }
}