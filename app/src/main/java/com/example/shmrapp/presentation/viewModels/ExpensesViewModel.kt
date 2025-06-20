package com.example.shmrapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.domain.usecase.GetTodayTransactionsUseCase
import com.example.shmrapp.presentation.models.ExpenseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class ExpensesTodayState(
    val expenses: List<ExpenseModel> = emptyList(),
    val totalAmount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ExpensesViewModel(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase,
):ViewModel() {
    private val _state = MutableStateFlow(ExpensesTodayState())
    val state = _state.asStateFlow()

    init {
        loadExpensesForToday()
    }

    fun loadExpensesForToday() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val today = getTodayInUtc()
                var totalAmount = 0.0
                val allTransactions = getTodayTransactionsUseCase.execute(startDate = today, endDate = today, isIncome = false)
                val expensesMapped = allTransactions.map {
                    totalAmount+=it.amount.toDouble()
                    ExpenseModel(
                        icon = it.category.emoji,
                        label = it.category.name,
                        amount = it.amount.toDouble().toInt().toString(),
                        comment = it.comment
                    )
                }
                _state.value = _state.value.copy(
                    isLoading = false,
                    expenses = expensesMapped,
                    error = null,
                    totalAmount = totalAmount.toInt()
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Ошибка загрузки: ${e.message}"
                )
            }
        }
    }

    private fun getTodayInUtc(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date())
    }
}