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
import com.example.shmrapp.presentation.composables.ExpenseItem
import com.example.shmrapp.presentation.models.ExpenseModel

val expensesMockList = listOf(
    ExpenseModel(
        icon = "\uD83C\uDFE0",
        label = "Аренда квартиры",
    ),
    ExpenseModel(
        icon = "\uD83D\uDC57",
        label = "Одежда",
    ),
    ExpenseModel(
        icon = "\uD83D\uDC36",
        label = "На собачку",
        comment = "Джек"
    ),
    ExpenseModel(
        icon = "\uD83D\uDC36",
        label = "На собачку",
        comment = "Энни"
    ),
    ExpenseModel(
        icon = "РК",
        label = "Ремонт квартиры",
    ),
    ExpenseModel(
        icon = "\uD83C\uDF6D",
        label = "Продукты",
    ),
    ExpenseModel(
        icon = "\uD83C\uDFCB\uFE0F\u200D♂\uFE0F",
        label = "Спортзал",
    ),
    ExpenseModel(
        icon = "\uD83D\uDC8A",
        label = "Медицина",
    )
)

@Preview(showBackground = true)
@Composable
fun ExpensesScreenContent() {
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
            Text(text = "436 558")
        }
        LazyColumn(
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            item { HorizontalDivider() }
            items(expensesMockList) {
                ExpenseItem(it)
                HorizontalDivider()
            }
        }
    }
}