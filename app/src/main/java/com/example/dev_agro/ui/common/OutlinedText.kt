package com.example.dev_agro.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

data class OutlinedTextFieldsProps(
    val value: String,
    val onChange: (String) -> Unit
)

@Composable
fun MyOutlinedTextField (props: OutlinedTextFieldsProps) {
    return OutlinedTextField(
        value = props.value,
        onValueChange = props.onChange,
        shape = RoundedCornerShape(15.dp)
    )
}
