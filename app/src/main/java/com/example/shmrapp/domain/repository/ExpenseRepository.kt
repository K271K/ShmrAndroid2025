package com.example.shmrapp.domain.repository

import com.example.shmrapp.domain.models.TransactionModel

interface ExpenseRepository {

    suspend fun getExpensesFromInternet(): List<TransactionModel>

}
