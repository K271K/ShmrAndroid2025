package com.example.shmrapp.domain.repository

import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.models.transactions.TransactionRequest

interface ExpenseRepository {

    suspend fun getTransactions(date: String): List<TransactionModel>

    suspend fun getTransactionById(id: Int): TransactionModel

    suspend fun createTransaction(request: TransactionRequest): TransactionCreateResponse

}
