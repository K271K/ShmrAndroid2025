package com.example.shmrapp.domain.usecase

import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.repository.TransactionsRepository

class GetTodayIncomesUseCase (
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(date: String): List<TransactionModel> {
        return transactionsRepository.getTransactionsByPeriod(
            accountId = 211,
            startDate = date,
            endDate = date
        )
    }
}