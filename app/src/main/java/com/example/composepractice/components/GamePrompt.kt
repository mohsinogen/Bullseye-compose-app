package com.example.composepractice.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.R

@Composable
fun GamePrompt(target: Int){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Text(text = stringResource(
            R.string.instruction_text),
            style = MaterialTheme.typography.titleMedium.copy(
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = target.toString(),
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GamePromptPreview(){
    GamePrompt(target = 5)
}