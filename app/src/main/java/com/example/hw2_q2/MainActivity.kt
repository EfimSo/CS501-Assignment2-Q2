package com.example.hw2_q2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hw2_q2.ui.theme.HW2Q2Theme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HW2Q2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val cal =  Calendar.getInstance();
    var fieldText by remember { mutableStateOf(value = "") }
    var greetingText by remember { mutableStateOf(value = "") }
    var greetingMessage by remember { mutableStateOf(value = "") }
    var displayed by remember { mutableStateOf(value = false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (displayed){
            Column(
                modifier = Modifier
                    .width(200.dp),
                horizontalAlignment = Alignment.Start
            ) {
                    Text(
                        text = greetingText,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .height(50.dp)
                    )
                    Text(
                        text = greetingMessage,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .height(50.dp),
                    )
            }
        }
        else {
            Spacer(modifier = Modifier.height(50.dp))
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = fieldText,
            onValueChange = { fieldText = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            greetingText = "Hello $fieldText"
            fieldText = ""

            val hourOfDay = cal.get(Calendar.HOUR_OF_DAY)
            greetingMessage = when (hourOfDay) {
                in 6..11 -> "Good Morning! \nEarly bird gets the worm."
                in 12..17 -> "Good Afternoon! \nHave a good day."
                in 18..23 -> "Good Evening! \nDon't stay up too late."
                in 0..5 -> "Good Night! \nSleep well."
                else -> "Invalid time!"
            }

            displayed = true
        }) {
            Text(
                text = "Greet me",
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HW2Q2Theme {
        Greeting()
    }
}

