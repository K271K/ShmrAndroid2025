package com.example.shmrapp.presentation.models

data class ExpenseModel(
    val icon: String = "\uD83D\uDCB0",
    val label: String = "Надпись",
    val comment: String = "",
    val amount: String = "100 000 Р",
)
