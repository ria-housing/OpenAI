package com.example.openai

import com.google.gson.annotations.SerializedName

data class Choices(
    @SerializedName("index"         ) var index        : Int?     = null,
    @SerializedName("message"       ) var message      : MessageAns? = MessageAns(),
    @SerializedName("finish_reason" ) var finishReason : String?  = null

)
