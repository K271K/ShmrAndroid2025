package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shmrapp.presentation.composables.MyListItemWithLeadIcon
import com.example.shmrapp.presentation.models.ExpenseModel
import com.example.shmrapp.presentation.theme.LightGreen

@Composable
fun ExpenseHistoryScreenContent() {
    val start = "Февраль 2025"
    val end = "23:41"
    val sum = "125 868 RUB"
    val mockList = listOf(
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
        ExpenseModel(),
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGreen)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Начало")
            Text(text = start)
        }
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGreen)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Конец")
            Text(text = end)
        }
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGreen)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Сумма")
            Text(text = sum)
        }
        HorizontalDivider()
        LazyColumn {
            items(mockList){expense->
                MyListItemWithLeadIcon(
                    icon = expense.icon,
                    iconBg = LightGreen,
                    content = {
                        Text(text = expense.label)
                    },
                    trailContent = {
                        Text(text = expense.amount)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    },
                    modifier = Modifier
                        .height(70.dp),
                )
                HorizontalDivider()
            }
        }
    }
}