package com.example.shmrapp.di

import com.example.shmrapp.data.repository.articles.ArticleRepositoryImplByRetrofit
import com.example.shmrapp.data.repository.transactions.TransactionsRepositoryImplByRetrofit
import com.example.shmrapp.domain.repository.ArticleRepository
import com.example.shmrapp.domain.repository.TransactionsRepository
import org.koin.dsl.module

val dataModule = module {

    single<TransactionsRepository> {
        TransactionsRepositoryImplByRetrofit()
    }

    single<ArticleRepository> {
        ArticleRepositoryImplByRetrofit()
    }

}