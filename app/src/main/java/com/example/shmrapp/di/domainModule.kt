package com.example.shmrapp.di

import com.example.shmrapp.domain.usecases.transactions.AddTransactionUseCase
import com.example.shmrapp.domain.usecases.transactions.GetTodayTransactionsUseCase
import com.example.shmrapp.domain.usecases.transactions.GetTransactionsByPeriodUseCase
import com.example.shmrapp.domain.usecases.article.GetArticlesFromServerUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetTodayTransactionsUseCase> {
        GetTodayTransactionsUseCase(transactionsRepository = get())
    }

    factory<GetArticlesFromServerUseCase> {
        GetArticlesFromServerUseCase(articleRepository = get())
    }

    factory<AddTransactionUseCase> {
        AddTransactionUseCase(transactionsRepository = get())
    }

    factory<GetTransactionsByPeriodUseCase> {
        GetTransactionsByPeriodUseCase(transactionsRepository = get())
    }

}