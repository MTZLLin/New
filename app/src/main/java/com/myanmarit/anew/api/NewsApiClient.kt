package com.myanmarit.anew.api

import com.myanmarit.anew.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NewsApiClient {
    val BASE_URL = "http://newsapi.org/v2/"
    val newsApiInterface: NewsApiInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApiInterface = retrofit.create(NewsApiInterface::class.java)
    }
    fun getTopHeadlines(country:String, category: String): Call<News> {
        return newsApiInterface.getTopHeadlines(
            country, category
        )
    }
}