package com.example.dev_agro.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dev_agro.R
import com.example.dev_agro.logic.LoginViewModel
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.ui.common.MyOutlinedTextField
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.utils.MESSAGE_WELCOME
import com.example.dev_agro.utils.monToast
import  com.example.dev_agro.ui.common.MyTitle
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps
import com.example.dev_agro.ui.theme.Green700

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel){
    val context = LocalContext.current
    val login = remember { mutableStateOf("" ) }
    val password = remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.messageToShowSharedFloat.collect {
            context.monToast(it)
            if (it.contains(MESSAGE_WELCOME))
                navController.navigate(Screen.Dashboard.route)
        }
    }

    LoginContent(
        loginState = login,
        passwordState = password,
        onValidate = { login, password ->
            viewModel.checkFormAndLogin(login, password)
        },
        goToRegister = {
            navController.navigate(Screen.Register.route)
        }
    )

}


@Composable
fun LoginContent(loginState : MutableState<String>,
                 passwordState: MutableState<String>,
                 onValidate : (String, String) -> Unit,
                 goToRegister : () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MyTitle(
            idText = R.string.login,
            modifier = Modifier.padding(top = 100.dp)
        )

        Spacer(modifier = Modifier.padding(50.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                value = loginState,
                placeholder = stringResource(R.string.login),
                variant = "TEXT",
                modifier = Modifier,
            ),
            isPassword = false
        )

        Spacer(modifier = Modifier.padding(20.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                value = passwordState,
                placeholder = stringResource(R.string.password),
                variant = "PASSWORD",
                modifier = Modifier
            ),
            isPassword = true
        )

        TextButton(onClick = goToRegister){
            Text(text = stringResource(R.string.link_to_register))
        }

        Spacer(modifier = Modifier.padding(40.dp))

        Button(
            onClick = { onValidate.invoke(loginState.value, passwordState.value) },
            modifier = Modifier
                .padding(top = 150.dp),
            colors =  buttonColors(
                containerColor = Green700
            )
        ) {
            Text(
                text = stringResource(R.string.to_login),
                color = Color.White
            )
        }
    }

}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Dev_AgroTheme {
        LoginContent(
            loginState = remember { mutableStateOf("" ) } ,
            passwordState = remember { mutableStateOf("" ) },
            onValidate = {_, _ -> },
            goToRegister = {}
        )
    }
}