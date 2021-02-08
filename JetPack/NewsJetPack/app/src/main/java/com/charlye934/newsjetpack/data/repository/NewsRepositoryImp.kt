package com.charlye934.newsjetpack.data.repository

import com.charlye934.newsjetpack.data.api.NewsApiService
import com.charlye934.newsjetpack.data.model.ApiResponse
import com.charlye934.newsjetpack.data.model.Article
import com.charlye934.newsjetpack.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImp(private val newsApiService: NewsApiService): NewsRepository {

    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse> {
        return responseToResources(newsApiService.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNew(searchQuery: String): Resource<ApiResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    private fun responseToResources(response: Response<ApiResponse>): Resource<ApiResponse>{
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}