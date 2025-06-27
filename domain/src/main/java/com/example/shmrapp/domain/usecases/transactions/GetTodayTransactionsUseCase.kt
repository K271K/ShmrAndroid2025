package com.example.shmrapp.domain.usecases.transactions

import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.repository.TransactionsRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class GetTodayTransactionsUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(
        accountId: Int = 211,
        isIncome: Boolean
    ) : Result<List<TransactionModel>> {
        val todayDate = getTodayInUtc()
        return transactionsRepository.getTransactionsByPeriod(
            accountId = accountId,
            startDate = todayDate,
            endDate = todayDate
        )
    }
    private fun getTodayInUtc(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date())
    }
}