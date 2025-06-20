package com.example.shmrapp.domain.models.transactions

data class TransactionRequest(
    val accountId: Int,
    val categoryId: Int,
    val amount: String,
    val transactionDate: String, // ISO 8601, UTC
    val comment: String? = null
)