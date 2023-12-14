package com.example.composepractice.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composepractice.R

@Composable
fun ResultDialog(
    hideDialog: () -> Unit,
    onRoundIncrement: () -> Unit,
    sliderValue: Int,
    points: Int,
    dialogTitle: Int,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = {
            hideDialog()
        },
        confirmButton = {
            TextButton(onClick = {
                hideDialog();
                onRoundIncrement()
            }) {
                Text(stringResource(R.string.result_dialog_btn_txt))
            }
        },
        title = {Text(stringResource(id = dialogTitle))},
        text = { Text(stringResource(R.string.result_dialog_msg, sliderValue, points)) }
        //text = { Text("The slider's value is $sliderValue") }
    )
}