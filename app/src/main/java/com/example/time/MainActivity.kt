package com.example.time

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.time.ui.theme.TimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimeTheme {
                StopWatch(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun StopWatch(modifier: Modifier = Modifier) {
    var timerValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerValue(
            onValueChange = { newValue ->
                timerValue = newValue
            }
        )
        Timer(timerValue = timerValue)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerValue(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val time = remember { mutableStateOf("") }
    val maxLength = 2

    Box {
        Column {
            TextField(
                value = time.value,
                onValueChange = { newValue ->
                    if(newValue.length <= maxLength){
                        if(newValue.all {it.isDigit()}) {
                            time.value = newValue
                            onValueChange(newValue)
                        }
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(top = 50.dp)
                    .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp)),
                placeholder = { Text(text = "Введите значение") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Blue,
                    focusedTextColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    val handler = Handler(Looper.getMainLooper())
                    fun startCountdown() {
                        val currentValue = time.value.toIntOrNull() ?: return
                        if (currentValue > 0) {
                            val newValue = (currentValue - 1).toString()
                            time.value = newValue
                            onValueChange(newValue)

                            handler.postDelayed({ startCountdown() }, 1000L)
                        }
                    }
                    startCountdown()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 50.dp)
                    .align(Alignment.CenterHorizontally),
                content = { Text(text = "Start") },
            )

            Button(
                onClick = {
                    time.value = ""
                    onValueChange("")
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 50.dp)
                    .align(Alignment.CenterHorizontally),
                content = { Text(text = "Clean") },
                colors = ButtonDefaults.buttonColors(Color.Red)
            )
        }
    }

}

@Composable
fun Timer(
    timerValue: String,
    modifier: Modifier = Modifier
) {

    Box {
        Text(
            text = timerValue,
            modifier = modifier
                .padding(16.dp)
                .padding(top = 50.dp),
            fontSize = 64.sp,
        )
    }
}

@Preview (showBackground = true)
@Composable
fun TimerValuePreview() {
    TimeTheme {
        TimerValue(
            onValueChange = { },
            modifier = Modifier.fillMaxWidth())
    }
}

@Preview (showBackground = true)
@Composable
fun TimerPreview() {
    TimeTheme {
        Timer(
            timerValue = "00:00:00",
            modifier = Modifier.fillMaxWidth()
        )
    }
}