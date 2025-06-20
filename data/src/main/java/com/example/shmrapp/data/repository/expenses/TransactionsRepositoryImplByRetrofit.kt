package com.example.shmrapp.data.repository.expenses

import android.util.Log
import com.example.shmrapp.data.network.RetrofitClient
import com.example.shmrapp.domain.models.transactions.TransactionCreateResponse
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.models.transactions.TransactionRequest
import com.example.shmrapp.domain.repository.TransactionsRepository
import retrofit2.HttpException

class TransactionsRepositoryImplByRetrofit: TransactionsRepository {

    private val retrofitApi = RetrofitClient.retrofitApi

    override suspend fun getTransactionsByPeriod(accountId: Int, startDate: String?, endDate: String?): List<TransactionModel> {
        val transactions = retrofitApi.getTransactionsByPeriod(accountId, startDate, endDate)
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