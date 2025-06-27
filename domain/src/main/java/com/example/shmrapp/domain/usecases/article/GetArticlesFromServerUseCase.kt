package com.example.shmrapp.domain.usecases.article

import com.example.shmrapp.domain.repository.ArticleRepository
import com.example.shmrfinanceapp.domain.models.CategoryModel

class GetArticlesFromServerUseCase(private val articleRepository: ArticleRepository) {

    suspend fun execute(): List<CategoryModel> {
        return articleRepository.getArticlesFromServer()
    }
}