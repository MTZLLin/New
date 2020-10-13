package com.myanmarit.anew.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myanmarit.anew.api.NewsApiClient
import com.myanmarit.anew.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var result: MutableLiveData<News> = MutableLiveData()
    private var errorStatus: MutableLiveData<Boolean> = MutableLiveData()
    private var errorMessage: MutableLiveData<String> = MutableLiveData()

    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getResult(): LiveData<News> = result
    fun getErrorStatus(): LiveData<Boolean> = errorStatus
    fun getErrorMessage(): LiveData<String> = errorMessage

    fun getLoading(): MutableLiveData<Boolean> = loading

    fun loadResult(){
        var apiClient = NewsApiClient()
        var apiCall = apiClient.getTopHeadlines("us","technology")

        apiCall.enqueue(object : Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                loading.value = false
                errorStatus.value = true
                errorMessage.value =t.toString()
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                loading.value = false
                errorStatus.value = false
                result.value = response.body()
            }

        })
    }
}