package com.example.shmrfinanceapp.domain.models

data class CategoryModel(
    val emoji: String,
    val id: Int,
    val isIncome: Boolean,
    val name: String
)