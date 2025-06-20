package com.example.shmrapp.di

import com.example.shmrapp.data.repository.articles.ArticleRepositoryImplByRetrofit
import com.example.shmrapp.data.repository.expenses.ExpenseRepositoryImplByRetrofit
import com.example.shmrapp.domain.repository.ArticleRepository
import com.example.shmrapp.domain.repository.ExpenseRepository
import org.koin.dsl.module
import kotlin.math.sin

val dataModule = module {

    single<ExpenseRepository> {
        ExpenseRepositoryImplByRetrofit()
    }

    single<ArticleRepository> {
        ArticleRepositoryImplByRetrofit()
    }
}