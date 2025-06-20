package com.example.shmrapp.data.repository.expenses

import android.util.Log
import com.example.shmrapp.data.network.RetrofitClient
import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.models.transactions.TransactionRequest
import com.example.shmrapp.domain.repository.ExpenseRepository
import retrofit2.HttpException

class ExpenseRepositoryImplByRetrofit: ExpenseRepository {

    private val retrofitApi = RetrofitClient.retrofitApi

    override suspend fun getTransactions(date: String): List<TransactionModel> {
        val today = date
        val transactions = retrofitApi.getTransactionsByPeriod(211, today, today)
        Log.d("ExpenseRepositoryImplByRetrofit", "getTransactions: $transactions")
        return transactions
    }

    override suspend fun getTransactionById(id: Int): TransactionModel {
        return retrofitApi.getTransactionById(id)
    }

    override suspend fun createTransaction(request: TransactionRequest): TransactionCreateResponse {
        return try {
            retrofitApi.createTransaction(request)
        } catch (e: HttpException){
            throw Exception("Ошибка создания: ${e.message}")
        }
    }
}