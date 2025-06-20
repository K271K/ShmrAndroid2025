package com.example.shmrapp.data.repository.articles

import android.util.Log
import com.example.shmrapp.data.network.RetrofitClient
import com.example.shmrapp.domain.repository.ArticleRepository
import com.example.shmrfinanceapp.domain.models.CategoryModel

class ArticleRepositoryImplByRetrofit : ArticleRepository {

    private val retrofitApi = RetrofitClient.retrofitApi

    override suspend fun getArticlesFromServer(): List<CategoryModel> {
        val list = retrofitApi.getCategories()
        Log.d("ArticleRepositoryImplByRetrofit","$list")
        return list
    }
}