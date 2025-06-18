package com.example.shmrapp.presentation.viewModels

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.domain.usecase.GetExpensesFromServerUseCase
import com.example.shmrapp.presentation.models.ExpenseModel
import kotlinx.coroutines.launch

class ExpensesViewModel(
    private val getExpensesFromServerUseCase: GetExpensesFromServerUseCase,
):ViewModel() {

    val expensesList = mutableStateOf(emptyList<ExpenseModel>())
    var totalAmount = mutableIntStateOf(0)
    val isLoading = mutableStateOf(true)

    init {
        viewModelScope.launch {
            println("started")
            val expenses = getExpensesFromServerUseCase.execute().map {
                totalAmount.intValue += it.amount.toDouble().toInt()
                ExpenseModel(
                    icon = it.category.emoji,
                    label = it.category.name,
                    comment = it.comment,
                    amount = it.amount
                )
            }
            expensesList.value = expenses
            isLoading.value = false
        }
    }
}