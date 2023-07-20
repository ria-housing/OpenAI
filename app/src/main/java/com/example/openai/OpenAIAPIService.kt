package com.example.openai

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIAPIService {
    @POST("completions")
    suspend fun getResponse(
        @Body requestBody: RequestBody,
        @Header("Content-Type") res : String = "application/json",
        @Header("Authorization") key : String = "Bearer $YOUR_API"
    ): Answer
    companion object{
        var apiService: OpenAIAPIService? = null
        fun getInstance() : OpenAIAPIService {
            if (apiService == null) {
                Log.d("comp","inside companion")
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.openai.com/v1/chat/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(OpenAIAPIService::class.java)
                Log.d("comp","outside companion")
            }
            return apiService!!
        }
    }
}
