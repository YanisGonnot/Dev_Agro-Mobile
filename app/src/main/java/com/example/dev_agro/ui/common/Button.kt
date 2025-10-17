package com.example.dev_agro.ui.common

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepButton(
    navigateToNextStep: () -> Unit,
    text: Int,
    modifierTextButton: Modifier,
    modifierText: Modifier
) {
    TextButton(
        onClick = navigateToNextStep,
        modifier = modifierTextButton
    ) {
        Text(
            text = stringResource(text),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifierText,
            color = Color.Black,
            fontStyle = FontStyle.Normal
        )
    }
}