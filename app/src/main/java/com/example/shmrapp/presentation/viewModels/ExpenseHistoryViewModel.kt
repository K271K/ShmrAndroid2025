package com.example.shmrapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.domain.models.transactions.TransactionModel
import com.example.shmrapp.domain.usecase.GetTransactionsByPeriodUseCase
import com.example.shmrapp.presentation.models.ExpenseForHistoryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class ExpenseHistoryState(
    val startDate: String = "",
    val endDate: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val amount: Int = 0,
    val transactions: List<ExpenseForHistoryModel> = emptyList() // Добавим список транзакций
)

class ExpenseHistoryViewModel(
    private val getTransactionsByPeriodUseCase: GetTransactionsByPeriodUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExpenseHistoryState())
    val state = _state.asStateFlow()

    init {
        setDefaultMonthDates()

        loadTransactions()

    }

    private fun setDefaultMonthDates() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Изменили формат
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        // Начало текущего месяца
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val startDate = sdf.format(calendar.time)
        // Конец текущего месяца
        calendar.add(Calendar.MONTH, 1)
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val endDate = sdf.format(calendar.time)

        _state.value = _state.value.copy(
            startDate = startDate,
            endDate = endDate
        )
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                var countedAmount = 0
                val transactions = getTransactionsByPeriodUseCase.execute(
                    accountId = 211, // Фиксированный accountId
                    startDate = _state.value.startDate,
                    endDate = _state.value.endDate,
                    isIncome = false, // Фильтр только расходов
                )

                val mappedTransactions = transactions.map {
                    countedAmount+=it.amount.toDouble().toInt()
                    ExpenseForHistoryModel(
                        icon = it.category.emoji,
                        label = it.category.name,
                        comment = it.comment,
                        amount = it.amount.toDouble().toInt().toString(),
                        time = formatTransactionDate(it.transactionDate)
                    )
                }

                Log.d("ExpenseHistoryViewModel", "Loaded transactions: $transactions")
                _state.value = _state.value.copy(
                    isLoading = false,
                    transactions = mappedTransactions,
                    amount = countedAmount
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Ошибка загрузки: ${e.message}"
                )
            }
        }
    }

    fun updateStartDate(dateInMillis: Long?) {
        try {
            if (dateInMillis != null) {
                val date = Date(dateInMillis)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                val formattedDate = sdf.format(date)
                _state.value = _state.value.copy(startDate = formattedDate)
                Log.d("ExpenseHistoryViewModel", "Updated start date: $formattedDate")
                viewModelScope.launch { loadTransactions() } // Обновляем данные
            }
        } catch (e: Exception) {
            _state.value = _state.value.copy(error = "Ошибка обновления даты: ${e.message}")
        }

    }

    fun updateEndDate(dateInMillis: Long?) {
        try {
            if (dateInMillis != null) {
                val date = Date(dateInMillis)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                val formattedDate = sdf.format(date)
                _state.value = _state.value.copy(endDate = formattedDate)
                viewModelScope.launch { loadTransactions() } // Обновляем данные
            }
        } catch (e: Exception) {
            _state.value = _state.value.copy(error = "Ошибка обновления даты: ${e.message}")
        }

    }

    fun formatTransactionDate(dateStr: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Учитываем UTC из 'Z'
            val date = inputFormat.parse(dateStr) ?: Date()

            val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            outputFormat.timeZone = TimeZone.getTimeZone("UTC") // Сохраняем в UTC
            outputFormat.format(date)
        } catch (e: Exception) {
            dateStr // Возвращаем исходную строку при ошибке
        }
    }

}