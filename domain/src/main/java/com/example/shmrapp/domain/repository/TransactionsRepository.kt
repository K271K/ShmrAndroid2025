package com.example.shmrapp.domain.repository

import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.models.transactions.TransactionRequest

interface TransactionsRepository {

    suspend fun getTransactionsByPeriod(accountId: Int, startDate: String? = null, endDate: String? = null): Result<List<TransactionModel>>

    suspend fun getTransactionById(id: Int): TransactionModel

    suspend fun createTransaction(request: TransactionRequest): TransactionCreateResponse

}
