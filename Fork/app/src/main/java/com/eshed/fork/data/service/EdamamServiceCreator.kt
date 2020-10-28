package com.eshed.fork.data.service

import android.util.Log
import com.eshed.fork.data.model.NutritionalAnalysisRequest
import com.eshed.fork.data.model.Recipe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object EdamamServiceCreator {

    const val BASE_URL = "https://api.edamam.com/"

    fun create(): EdamamService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(EdamamService::class.java)
    }

//    init {
//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//        val jsonAdapter =
//            moshi.adapter(Recipe::class.java)
//        val json = jsonAdapter.toJson(recipe)
//        Log.d("TAG", "NutritionViewModel: \n$json")
//    }

//    private var retrofit: Retrofit? = null
//    fun getClient(baseUrl: String?): Retrofit? {
//        if (retrofit == null) {
//            retrofit = Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//        return retrofit
//    }
}