package com.example.composepractice.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepractice.R
import com.example.composepractice.components.GameDetail
import com.example.composepractice.components.GamePrompt
import com.example.composepractice.components.ResultDialog
import com.example.composepractice.components.TargetSlider
import com.example.composepractice.ui.theme.ComposePracticeTheme
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun GameScreen() {

    var alertIsVisible by rememberSaveable { mutableStateOf(false) }
    var sliderValue by rememberSaveable { mutableStateOf(0.0f) }

    var targetValue by rememberSaveable {
        mutableStateOf(Random.nextInt(1,100))
    }

    val sliderToInt = (sliderValue * 100).toInt()

    var totalScore by rememberSaveable {
        mutableStateOf(0)
    }
    var currentRound by rememberSaveable {
        mutableStateOf(1)
    }

    fun calculateResult():Int{
        val maxScore = 100
        val difference = abs(targetValue - sliderToInt)

        var bonus = 0;

        if(difference==0){
            bonus = 100
        } else if(difference == 1) {
            bonus = 50
        }
        return (maxScore - difference)+bonus
    }

    fun startOver(): Unit{
        currentRound = 1
        totalScore = 0
        targetValue = Random.nextInt(1,100)
    }

    fun alertTitle():Int {
        val difference = abs(targetValue - sliderToInt)

        val title: Int = when {
            difference == 0 -> {
                R.string.alert_title1
            }
            difference < 5 -> {
                R.string.alert_title2
            }
            difference <= 10 -> {
                R.string.alert_title3
            }
            else -> {
                R.string.alert_title4
            }
        }

        return title
    }

    Box {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background),
            contentDescription = stringResource(R.string.background_image)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(.5f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(9f)
            ) {
                GamePrompt(target = targetValue)
                TargetSlider(value = sliderValue, valueChange = { value ->
                    sliderValue = value
                })
                Button(
                    onClick = {
                        alertIsVisible = true
                        totalScore += calculateResult()
                    },
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = stringResource(id = R.string.hit_me_btn_txt))
                }
                GameDetail(
                    totalScore = totalScore,
                    currentRound = currentRound,
                    startHandler = { startOver() },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.weight(.5f))

            if (alertIsVisible) {
                ResultDialog(hideDialog = {
                    alertIsVisible = false
                }, sliderValue = sliderToInt, points = calculateResult(), onRoundIncrement = {
                    currentRound += 1
                    targetValue = Random.nextInt(1, 100)
                }, dialogTitle = alertTitle())
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 432)
@Composable
fun GameScreenPreview() {
    ComposePracticeTheme {
        GameScreen()
    }
}