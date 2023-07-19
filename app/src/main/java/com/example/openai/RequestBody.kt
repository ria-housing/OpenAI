package com.example.openai

import com.google.gson.annotations.SerializedName

data class RequestBody(
    @SerializedName("model"    ) var model: String?             = "gpt-3.5-turbo",
    @SerializedName("messages" ) var messages: ArrayList<Messages> = arrayListOf()
)
