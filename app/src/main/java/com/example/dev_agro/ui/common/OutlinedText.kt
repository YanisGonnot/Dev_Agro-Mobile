package com.example.dev_agro.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dev_agro.ui.theme.Green200
import com.example.dev_agro.ui.theme.Green400
import androidx.compose.ui.Modifier

data class OutlinedTextFieldsProps(
    val value: MutableState<String>,
    val placeholder: String,
    val variant: String,
    val modifier: Modifier,
)

@Composable
fun MyOutlinedTextField (props: OutlinedTextFieldsProps) {
    return OutlinedTextField(
        value = props.value.value,
        onValueChange = {
            props.value.value = it
        },
        placeholder = {
            Text(text = props.placeholder)
        },
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Green400,
            unfocusedContainerColor = Green200,
        ),
        modifier = props.modifier
    )
}
