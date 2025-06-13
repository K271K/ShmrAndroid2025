package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExpenseHistoryScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    ) {
        Text(text = "Экран истории расходов")
    }
}