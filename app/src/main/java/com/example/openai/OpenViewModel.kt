package com.example.openai

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OpenViewModel : ViewModel() {
    var OpenAIAnswer:Answer by mutableStateOf(Answer())
    var errorMessage: String by mutableStateOf("")
    fun getAnswerInstance(requestBody: RequestBody){

        viewModelScope.launch {
            val apiServ = OpenAIAPIService.getInstance()
            try {
                Log.d("hello","hello")
                val ans = apiServ.getResponse(requestBody = requestBody)
                Log.d("t","$ans")
                OpenAIAnswer = ans
            }
            catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }
}