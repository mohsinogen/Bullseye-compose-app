package com.example.composepractice

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ResultDialog(
    hideDialog: () -> Unit,
    sliderValue: Int,
    modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = {
            hideDialog()
        },
        confirmButton = {
            TextButton(onClick = {
                hideDialog();
            }) {
                Text(stringResource(R.string.result_dialog_btn_txt))
            }
        },
        title = {Text(stringResource(R.string.result_dialog_title))},
        text = { Text(stringResource(R.string.result_dialog_msg, sliderValue)) }
        //text = { Text("The slider's value is $sliderValue") }
    )
}