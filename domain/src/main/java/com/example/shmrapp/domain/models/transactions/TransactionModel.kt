package com.example.shmrapp.domain.models.transactions

data class TransactionModel(
    val account: Account,
    val amount: String,
    val category: Category,
    val comment: String,
    val createdAt: String,
    val id: Int,
    val transactionDate: String,
    val updatedAt: String
)

data class Category(
    val emoji: String,
    val id: Int,
    val isIncome: Boolean,
    val name: String
)

data class Account(
    val balance: String,
    val currency: String,
    val id: Int,
    val name: String
)