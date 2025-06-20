package com.example.shmrapp.domain.usecase

import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.models.transactions.TransactionRequest
import com.example.shmrapp.domain.repository.ExpenseRepository

class GetExpensesFromServerUseCase(
    private val expenseRepository: ExpenseRepository
) {
    suspend fun execute(date: String): List<TransactionModel> {
        val transactions = expenseRepository.getTransactions(date)
        return transactions
    }
}

class AddExpenseUseCase(private val expenseRepository: ExpenseRepository) {
    suspend operator fun invoke(request: TransactionRequest): TransactionCreateResponse {
        return expenseRepository.createTransaction(request)
    }
}