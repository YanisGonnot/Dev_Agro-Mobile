package com.example.dev_agro.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun Context.monToast(message:String, duration:Int = Toast.LENGTH_SHORT)
        = Toast.makeText(this, message, duration).show()

@Composable
fun monContext() = LocalContext.current