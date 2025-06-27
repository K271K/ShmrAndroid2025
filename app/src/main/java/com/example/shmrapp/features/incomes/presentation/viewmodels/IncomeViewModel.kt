package com.example.shmrapp.features.incomes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.domain.usecases.transactions.GetTodayTransactionsUseCase
import com.example.shmrapp.features.incomes.presentation.models.IncomeUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class IncomesTodayState(
    val incomes: List<IncomeUiModel> = emptyList(),
    val totalAmount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

class IncomeViewModel(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(IncomesTodayState())
    val state = _state.asStateFlow()

    init {
        loadIncomesForToday()
    }

    private fun loadIncomesForToday() {
        viewModelScope.launch {

            var totalAmount = 0.0
            getTodayTransactionsUseCase(
                isIncome = true
            )
                .onSuccess { successTransactions->
                    val incomesMapped = successTransactions.map {
                        totalAmount += it.amount.toDouble()
                        IncomeUiModel(
                            id = it.id,
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
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = "Ошибка загрузки: ${it.message}"
                    )
                }
        }
    }

}
