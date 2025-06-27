package com.example.shmrapp.features.expense.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.core.ext.toUiMessage
import com.example.shmrapp.domain.usecases.transactions.GetTodayTransactionsUseCase
import com.example.shmrapp.features.expense.presentation.models.ExpenseUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ExpensesTodayState(
    val expenses: List<ExpenseUiModel> = emptyList(),
    val totalAmount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ExpensesViewModel(
    private val getTodayTransactionsUseCase: GetTodayTransactionsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ExpensesTodayState())
    val state = _state.asStateFlow()

    init {
        loadExpensesForToday()
    }

    fun loadExpensesForToday() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            var totalAmount = 0.0
            getTodayTransactionsUseCase(isIncome = false)
                .onSuccess { successList ->
                    val expensesMapped = successList.map {
                        totalAmount += it.amount.toDouble()
                        ExpenseUiModel(
                            id = it.id,
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
                }
                .onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = it.message.toUiMessage()
                    )
                }
        }
    }
}
