package com.example.shmrapp.di

import com.example.shmrapp.domain.usecase.AddExpenseUseCase
import com.example.shmrapp.domain.usecase.GetArticlesFromServerUseCase
import com.example.shmrapp.domain.usecase.GetExpensesFromServerUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetExpensesFromServerUseCase> {
        GetExpensesFromServerUseCase(expenseRepository = get())
    }

    factory<GetArticlesFromServerUseCase> {
        GetArticlesFromServerUseCase(articleRepository = get())
    }

    factory<AddExpenseUseCase> {
        AddExpenseUseCase(expenseRepository = get())
    }

}