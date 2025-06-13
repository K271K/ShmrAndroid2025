package com.example.shmrfinanceapp.domain.models

data class BankAccountModel(
    val balance: String,
    val createdAt: String,
    val currency: String,
    val id: Int,
    val name: String,
    val updatedAt: String,
    val userId: Int
)