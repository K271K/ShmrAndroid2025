package com.example.shmrapp.domain.usecases.transactions

import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.repository.TransactionsRepository

class GetTransactionsByPeriodUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend fun execute(
        accountId: Int = 211,
        startDate: String,
        endDate: String,
        isIncome: Boolean
    ): Result<List<TransactionModel>> {
        return transactionsRepository.getTransactionsByPeriod(
            accountId = accountId,
            startDate = startDate,
            endDate = endDate
        )
    }
}