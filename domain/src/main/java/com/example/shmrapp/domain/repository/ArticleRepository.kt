package com.example.shmrapp.domain.repository

import com.example.shmrfinanceapp.domain.models.CategoryModel

interface ArticleRepository {

    suspend fun getArticlesFromServer(): List<CategoryModel>

}
