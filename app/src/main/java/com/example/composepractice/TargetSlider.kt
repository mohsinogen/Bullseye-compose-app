package com.example.composepractice

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TargetSlider(
    value: Float = 0.5f,
    valueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.min_val_txt),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp)
        )
        Slider(
            value = value,
            valueRange = 0.01f..1f,
            onValueChange = valueChange,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(id = R.string.max_val_txt),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}