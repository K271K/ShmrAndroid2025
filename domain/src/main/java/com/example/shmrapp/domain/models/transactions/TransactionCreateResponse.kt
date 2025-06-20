package com.example.shmrapp.domain.models.transactions

data class TransactionCreateResponse(
    val accountId: Int,
    val amount: String,
    val categoryId: Int,
    val comment: String,
    val createdAt: String,
    val id: Int,
    val transactionDate: String,
    val updatedAt: String
)