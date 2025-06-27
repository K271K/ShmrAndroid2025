package com.example.shmrapp.domain.usecases.transactions

import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionRequest
import com.example.shmrapp.domain.repository.TransactionsRepository

class AddTransactionUseCase(private val transactionsRepository: TransactionsRepository) {
    suspend operator fun invoke(request: TransactionRequest): TransactionCreateResponse {
        return transactionsRepository.createTransaction(request)
    }
}