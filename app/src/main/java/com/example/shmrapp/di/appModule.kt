package com.example.shmrapp.di

import com.example.shmrapp.core.ui.viewmodels.AddTransactionViewModel
import com.example.shmrapp.features.account.presentation.viewmodels.ArticlesViewModel
import com.example.shmrapp.core.ui.viewmodels.TransactionHistoryViewModel
import com.example.shmrapp.features.expense.presentation.viewmodels.ExpensesViewModel
import com.example.shmrapp.features.incomes.presentation.viewmodels.IncomeViewModel
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    viewModel<ExpensesViewModel> {
        ExpensesViewModel(getTodayTransactionsUseCase = get())
    }

    viewModel<ArticlesViewModel> {
        ArticlesViewModel(getArticlesFromServerUseCase = get())
    }

    viewModel<AddTransactionViewModel> {
        AddTransactionViewModel(addTranscationUseCase = get())
    }

    viewModel<IncomeViewModel> {
        IncomeViewModel(getTodayTransactionsUseCase = get())
    }

    viewModel<TransactionHistoryViewModel> {
        TransactionHistoryViewModel(getTransactionsByPeriodUseCase = get(), isIncome = get())
    }
}