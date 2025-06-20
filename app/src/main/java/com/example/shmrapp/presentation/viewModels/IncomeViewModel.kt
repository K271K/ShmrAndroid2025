package com.example.shmrapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.domain.usecase.GetTodayTransactionsUseCase
import com.example.shmrapp.presentation.models.IncomeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class IncomesTodayState(
    val incomes: List<IncomeModel> = emptyList(),
    val totalAmount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

class IncomeViewModel(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase
) : ViewModel () {
    private val _state = MutableStateFlow(IncomesTodayState())
    val state = _state.asStateFlow()

    init {
        loadIncomesForToday()
    }

    private fun loadIncomesForToday() {
        viewModelScope.launch {
            try {
                val today = getTodayInUtc()
                var totalAmount = 0.0
                val allTransactions = getTodayTransactionsUseCase.execute(
                    startDate = null,
                    endDate = null,
                    isIncome = true
                )
                val incomesMapped = allTransactions.map {
                    totalAmount += it.amount.toDouble()
                    IncomeModel(
                        label = it.category.name,
                        amount = it.amount.toDouble().toInt().toString()
                    )
                }
                _state.value = _state.value.copy(
                    isLoading = false,
                    incomes = incomesMapped,
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
