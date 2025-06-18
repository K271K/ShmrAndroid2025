package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shmrapp.presentation.composables.MyListItemOnlyText
import com.example.shmrapp.presentation.models.IncomeModel
import com.example.shmrapp.presentation.theme.LightGreen

val incomesMockList = listOf(
    IncomeModel(
        label = "Зарплата",
        amount = "500 000 ₽"
    ),
    IncomeModel(
        label = "Подработка",
        amount = "100 000 ₽"
    )
)

@Preview(showBackground = true)
@Composable
fun IncomeScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyListItemOnlyText(
            modifier = Modifier
                .height(56.dp)
                .background(LightGreen),
            content = {
                Text(text = "Всего")
            },
            trailContent = {
                Text(text = "436 558 R")
            }
        )
        HorizontalDivider()
        LazyColumn(
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            items(incomesMockList){
                MyListItemOnlyText(
                    modifier = Modifier
                        .height(73.dp),
                    content = {
                        Text(text = it.label)
                    },
                    trailContent = {
                        Text(text = it.amount)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                            contentDescription = "Подробнее",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                HorizontalDivider()
            }
        }
    }
}