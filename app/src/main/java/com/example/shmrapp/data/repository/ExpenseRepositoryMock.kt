package com.example.shmrapp.data.repository

import com.example.shmrapp.domain.models.Account
import com.example.shmrapp.domain.models.Category
import com.example.shmrapp.domain.models.TransactionModel
import com.example.shmrapp.domain.repository.ExpenseRepository
import kotlinx.coroutines.delay

class ExpenseRepositoryMock:ExpenseRepository {
    override suspend fun getExpensesFromInternet(): List<TransactionModel> {
        delay(2000L)
        return listOf(
            TransactionModel(
                account = Account(
                    balance = "400.00",
                    currency = "RUB",
                    id = 211,
                    name = "Банковская карта"
                ),
                amount = "500.00",
                category = Category(
                    emoji = "\uD83C\uDF4E",
                    id = 8,
                    isIncome = false,
                    name = "Продукты"
                ),
                comment = "",
                createdAt = "2025-06-16T12:40:39.038976Z",
                id = 55,
                transactionDate = "2025-06-16T08:55:10.028Z",
                updatedAt = "2025-06-16T12:40:39.038976Z"
            ),
            TransactionModel(
                account = Account(
                    balance = "100000",
                    currency = "$",
                    id = 1,
                    name = "Карта пэй"
                ),
                amount = "200",
                category = Category(
                    emoji = "\uD83C\uDFCB\uFE0F\u200D♂\uFE0F",
                    id = 2,
                    isIncome = false,
                    name = "Спортзал"
                ),
                comment = "Тест",
                createdAt = "15.06.2025",
                id = 2,
                transactionDate = "15.06.2025",
                updatedAt = "15.06.2025"
            ),
        )
    }
}