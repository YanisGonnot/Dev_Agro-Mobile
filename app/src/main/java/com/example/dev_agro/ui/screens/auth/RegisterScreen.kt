package com.example.dev_agro.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dev_agro.R
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.ui.common.MyOutlinedTextField
import com.example.dev_agro.ui.common.MyTitle
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.ui.theme.Green700

@Composable
fun RegisterScreen(navController: NavController){
    val login = remember { mutableStateOf("" ) }
    val password = remember { mutableStateOf("" ) }
    val confirmPassword = remember { mutableStateOf("" ) }

    RegisterContent(
        login = login,
        password = password,
        confirmPassword = confirmPassword,
        goToMain = { navController.navigate(Screen.OnBoarding.route) }
    )
}


@Composable
fun RegisterContent(login : MutableState<String>,
                    password : MutableState<String>,
                    confirmPassword : MutableState<String>,
                    goToMain : () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MyTitle(
            idText = R.string.new_account,
            modifier = Modifier.padding(top = 150.dp)
        )

        Spacer(modifier = Modifier.padding(50.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                value = login,
                placeholder = stringResource(R.string.login),
                variant = "TEXT",
                modifier = Modifier
            )
        )

        Spacer(modifier = Modifier.padding(20.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                value = password,
                placeholder = stringResource(R.string.password),
                variant = "PASSWORD",
                modifier = Modifier
            )
        )

        Spacer(modifier = Modifier.padding(20.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                value = confirmPassword,
                placeholder = stringResource(R.string.confirm_password),
                variant = "PASSWORD",
                modifier = Modifier
            )
        )

        Button(
            onClick = { goToMain() },
            modifier = Modifier
                .padding(top = 150.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Green700
            )
        ) {
            Text(text = stringResource(id = R.string.to_register))
        }
    }
}




@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    Dev_AgroTheme {
        RegisterContent(
            login = remember { mutableStateOf("" ) },
            password = remember { mutableStateOf("" ) },
            confirmPassword = remember { mutableStateOf("" ) },
            goToMain = { }
        )

    }
}