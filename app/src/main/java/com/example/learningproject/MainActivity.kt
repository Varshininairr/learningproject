package com.example.learningproject

import android.R
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningproject.ui.theme.LearningProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var typedNames =  remember {
                mutableStateOf("")
            }
            var listOfNames = remember {
                mutableStateOf(listOf<String>())
            }
            LearningProjectTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                       BasicTextField(
                           modifier = Modifier.width(200.dp).height(50.dp).background(color = androidx.compose.ui.graphics.Color.Red),
                           value = typedNames.value, onValueChange = { text ->
                           typedNames.value =  text})

                        Button(
                            modifier = Modifier.size(80.dp),
                            shape = RectangleShape,
                            content = { Text(text = "Click") },
                            onClick = {
                                listOfNames.value = listOfNames.value + typedNames.value
                            }
                        )

                }
                    LazyColumn() {
                        items(listOfNames.value){ name ->
                            Text(text = name, modifier = Modifier.padding(10.dp), color = androidx.compose.ui.graphics.Color.Black)
                        }
                    }
            }}
        }
    }
}
