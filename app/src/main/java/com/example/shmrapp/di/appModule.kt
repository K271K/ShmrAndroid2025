package com.example.shmrapp.di

import com.example.shmrapp.presentation.viewModels.AddExpenseViewModel
import com.example.shmrapp.presentation.viewModels.ArticlesViewModel
import com.example.shmrapp.presentation.viewModels.ExpenseHistoryViewModel
import com.example.shmrapp.presentation.viewModels.ExpensesViewModel
import com.example.shmrapp.presentation.viewModels.IncomeViewModel
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    viewModel<ExpensesViewModel> {
        ExpensesViewModel(getTodayTransactionsUseCase = get())
    }

    viewModel<ArticlesViewModel> {
        ArticlesViewModel(getArticlesFromServerUseCase = get())
    }

    viewModel<AddExpenseViewModel> {
        AddExpenseViewModel(addTranscationUseCase = get())
    }

    viewModel<IncomeViewModel> {
        IncomeViewModel(getTodayTransactionsUseCase = get())
    }

    viewModel<ExpenseHistoryViewModel> {
        ExpenseHistoryViewModel(getTransactionsByPeriodUseCase = get())
    }
}