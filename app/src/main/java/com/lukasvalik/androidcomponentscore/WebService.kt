package com.lukasvalik.androidcomponentscore

import android.arch.lifecycle.LiveData
import com.lukasvalik.kotlinandroidcomponents.api.ApiResponse
import retrofit2.http.GET

interface WebService {

    companion object {
        const val BASE_URL = ""
    }

    @GET("airlines")
    fun getAirlines(): LiveData<ApiResponse<List<Airline>>>
}