package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shmrapp.presentation.composables.MyListItemOnlyText

@Composable
fun AddExpenseScreenContent(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyListItemOnlyText(
            content = {
                Text(text = "Счёт")
            },
            trailContent = {
                Text(text = "Сбербанк")
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = "Подробнее",
                )
            },
            modifier = Modifier
                .height(70.dp),
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Статья")
            },
            trailContent = {
                Text(text = "Ремонт")
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = "Подробнее",
                )
            },
            modifier = Modifier
                .height(70.dp)
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Сумма")
            },
            trailContent = {
                Text(text = "25 270 R")
            },
            modifier = Modifier
                .height(70.dp)
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Дата")
            },
            trailContent = {
                Text(text = "25.02.2025")
            },
            modifier = Modifier
                .height(70.dp)
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Время")
            },
            trailContent = {
                Text(text = "23:41")
            },
            modifier = Modifier
                .height(70.dp)
        )
        HorizontalDivider()
        MyListItemOnlyText(
            content = {
                Text(text = "Ремонт - фурнитура для дверей")
            },
            trailContent = {
            },
            modifier = Modifier
                .height(70.dp)
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified
            )
        ) {
            Text(text = "Удалить расход", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}