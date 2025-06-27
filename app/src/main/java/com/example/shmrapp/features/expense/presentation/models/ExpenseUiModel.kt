package com.example.shmrapp.features.expense.presentation.models

data class ExpenseUiModel(
    val id: Int,
    val icon: String = "\uD83D\uDCB0",
    val label: String = "Надпись",
    val comment: String = "",
    val amount: String = "100 000 Р",
)