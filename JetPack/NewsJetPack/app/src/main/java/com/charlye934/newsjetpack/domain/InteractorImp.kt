package com.charlye934.newsjetpack.domain

import com.charlye934.newsjetpack.data.model.ApiResponse
import com.charlye934.newsjetpack.data.model.Article
import com.charlye934.newsjetpack.data.repository.NewsRepository
import com.charlye934.newsjetpack.util.Resource
import kotlinx.coroutines.flow.Flow

class InteractorImp(private val newsRepository: NewsRepository ): Interactor {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }

    override suspend fun getSearchedNew(searchQuery: String): Resource<ApiResponse> {
        return newsRepository.getSearchedNew(searchQuery)
    }

    override suspend fun saveNews(article: Article) {
        newsRepository.saveNews(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsRepository.deleteNews(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}