package com.myanmarit.anew.api

import com.myanmarit.anew.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiInterface {
    @Headers("X-Api-Key: 31f0048a432848d6b08eaa7f95960f25")
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String
    ): Call<News>
}