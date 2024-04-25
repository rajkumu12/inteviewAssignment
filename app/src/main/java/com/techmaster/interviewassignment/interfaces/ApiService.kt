package com.techmaster.interviewassignment.interfaces

import com.techmaster.interviewassignment.BuildConfig
import com.techmaster.interviewassignment.data.PostModels
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getAllPosts(@Query("page") page: Int, @Query("limit") limit: Int): List<PostModels>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL) // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}