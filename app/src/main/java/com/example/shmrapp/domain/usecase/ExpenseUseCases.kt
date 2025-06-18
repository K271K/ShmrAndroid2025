package com.example.shmrapp.domain.usecase

import com.example.shmrapp.domain.models.TransactionModel
import com.example.shmrapp.domain.repository.ExpenseRepository

class GetExpensesFromServerUseCase(
    private val expenseRepository: ExpenseRepository
) {
    suspend fun execute(): List<TransactionModel> {
        return expenseRepository.getExpensesFromInternet()
    }
}