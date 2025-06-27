package com.example.shmrapp.features.account.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shmrapp.domain.usecases.article.GetArticlesFromServerUseCase
import com.example.shmrfinanceapp.domain.models.CategoryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ArticleScreenState(
    val articles: List<CategoryModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ArticlesViewModel(
    private val getArticlesFromServerUseCase: GetArticlesFromServerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ArticleScreenState())
    val state = _state.asStateFlow()

    init {
        loadArticles()
    }

    fun loadArticles() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val articlesFromData = getArticlesFromServerUseCase.execute()
                val articlesMapped = articlesFromData.map { articleDataModel ->
                    CategoryModel(
                        emoji = articleDataModel.emoji,
                        id = articleDataModel.id,
                        isIncome = articleDataModel.isIncome,
                        name = articleDataModel.name
                    )
                }
                _state.value = _state.value.copy(
                    articles = articlesMapped,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}