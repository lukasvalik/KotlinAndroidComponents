package com.lukasvalik.androidcomponentscore

import android.app.Application
import com.lukasvalik.kotlinandroidcomponents.AppExecutors
import com.lukasvalik.kotlinandroidcomponents.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var webService: WebService
        private set
    lateinit var appExecutors: AppExecutors
        private set
    lateinit var cache: Cache
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this
        appExecutors = AppExecutors()
        cache = Cache()
        webService = Retrofit.Builder()
                .baseUrl(WebService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(WebService::class.java)
    }
}