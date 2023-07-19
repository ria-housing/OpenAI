package com.example.openai

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.openai.ui.theme.OpenAITheme

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<OpenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenAITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    MainScreen(applicationContext,mainViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(applicationContext: Context, mainViewModel: OpenViewModel) {
    var ques by remember {
        mutableStateOf("")
    }
    var bool by remember {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .padding(all = 10.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = ques,
            onValueChange = {
                ques = it
            },
            modifier = Modifier
                .padding(vertical = 10.dp),
            placeholder = { Text(text = "Enter your question") },
        )
        var arrayList = ArrayList<Messages>()
        arrayList.add(Messages("user",ques))
        Button(onClick = {
            mainViewModel.getAnswerInstance(RequestBody(messages = arrayList))
        }) {
            Text(text = "Submit")
        }
        mainViewModel.OpenAIAnswer.choices.forEach{
            Text(text = "${it.message?.content}")
        }
//        Log.d("answer","${mainViewModel.OpenAIAnswer}")
    }
}

