package com.example.shmrapp.presentation.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shmrapp.R
import com.example.shmrapp.presentation.composables.IncomeItem
import com.example.shmrapp.presentation.models.IncomeModel

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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.green_light))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Всего")
            Text(text = "600 000 ₽")
        }
        HorizontalDivider()
        LazyColumn(
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            items(incomesMockList){
                IncomeItem(it)
                HorizontalDivider()
            }
        }
    }
}