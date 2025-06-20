package com.example.shmrapp.domain.usecase

import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.models.transactions.TransactionRequest
import com.example.shmrapp.domain.repository.TransactionsRepository

class GetTodayTransactionsUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend fun execute(
        accountId: Int = 211,
        startDate: String? = null,
        endDate: String? = null,
        isIncome: Boolean? = null
    ): List<TransactionModel> {
        val transactions = transactionsRepository.getTransactionsByPeriod(accountId, startDate, endDate)
        return if (isIncome != null) {
            transactions.filter { it.category.isIncome == isIncome }
        } else {
            transactions
        }
    }
}

class GetTransactionsByPeriodUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend fun execute(
        accountId: Int = 211,
        startDate: String? = null,
        endDate: String? = null,
        isIncome: Boolean? = null
    ) : List<TransactionModel>{
        val transactions = transactionsRepository.getTransactionsByPeriod(accountId, startDate, endDate).sortedBy { it.transactionDate }
        return if (isIncome != null) {
            transactions.filter { it.category.isIncome == isIncome }
        } else {
            transactions
        }
    }

}

class AddTranscationUseCase(private val transactionsRepository: TransactionsRepository) {
    suspend operator fun invoke(request: TransactionRequest): TransactionCreateResponse {
        return transactionsRepository.createTransaction(request)
    }
}