package com.example.shmrapp.di

import com.example.shmrapp.presentation.viewModels.AddExpenseViewModel
import com.example.shmrapp.presentation.viewModels.ArticlesViewModel
import com.example.shmrapp.presentation.viewModels.ExpensesViewModel
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    viewModel<ExpensesViewModel> {
        ExpensesViewModel(getExpensesFromServerUseCase = get())
    }

    viewModel<ArticlesViewModel> {
        ArticlesViewModel(getArticlesFromServerUseCase = get())
    }

    viewModel<AddExpenseViewModel> {
        AddExpenseViewModel(addExpenseUseCase = get())
    }
}