package com.charlye934.newsjetpack.domain

import com.charlye934.newsjetpack.data.model.ApiResponse
import com.charlye934.newsjetpack.data.model.Article
import com.charlye934.newsjetpack.util.Resource
import kotlinx.coroutines.flow.Flow

interface Interactor {
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse>
    suspend fun getSearchedNew(searchQuery: String): Resource<ApiResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}