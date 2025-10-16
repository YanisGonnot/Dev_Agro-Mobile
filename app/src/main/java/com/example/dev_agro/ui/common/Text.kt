package com.example.dev_agro.ui.common

import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.dev_agro.ui.theme.DevAgro


@Composable
fun MyTitle(idText : Int, modifier: Modifier) {
    Text(
        text = stringResource(id = idText),
        modifier = modifier,
        color = DevAgro,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
    )
}