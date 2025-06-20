package com.example.shmrapp.di

import com.example.shmrapp.domain.usecase.AddTranscationUseCase
import com.example.shmrapp.domain.usecase.GetArticlesFromServerUseCase
import com.example.shmrapp.domain.usecase.GetTodayTransactionsUseCase
import com.example.shmrapp.domain.usecase.GetTransactionsByPeriodUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetTodayTransactionsUseCase> {
        GetTodayTransactionsUseCase(transactionsRepository = get())
    }

    factory<GetArticlesFromServerUseCase> {
        GetArticlesFromServerUseCase(articleRepository = get())
    }

    factory<AddTranscationUseCase> {
        AddTranscationUseCase(transactionsRepository = get())
    }

    factory<GetTransactionsByPeriodUseCase> {
        GetTransactionsByPeriodUseCase(transactionsRepository = get())
    }

}