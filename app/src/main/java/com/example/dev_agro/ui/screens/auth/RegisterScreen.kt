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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dev_agro.R
import com.example.dev_agro.logic.RegisterViewModel
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.ui.common.MyOutlinedTextField
import com.example.dev_agro.ui.common.MyTitle
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps
import com.example.dev_agro.ui.theme.DevAgro
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.utils.MESSAGE_WELCOME
import com.example.dev_agro.utils.monToast

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel){
    val context = LocalContext.current
    val loginState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }



    LaunchedEffect(key1 = Unit) {
        viewModel.messageToShowSharedFloat.collect {
            context.monToast(it)
            if (it.contains(MESSAGE_WELCOME))
                navController.navigate(Screen.Dashboard.route){
                    popUpTo(Screen.Register.route){
                        inclusive = true
                    }
                }
        }
    }

    RegisterContent(
        loginState = loginState,
        passwordState = passwordState,
        confirmPassword = confirmPasswordState,
        goToMain = { username, password, password2 ->
            viewModel.checkFormAndRegister(username, password, password2)
        }
    )

}


@Composable
fun RegisterContent(loginState : MutableState<String>,
                    passwordState : MutableState<String>,
                    confirmPassword : MutableState<String>,
                    goToMain : (String, String, String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MyTitle(
            idText = R.string.new_account,
            modifier = Modifier.padding(top = 80.dp)
        )

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                text = loginState,
                idPlaceholder = R.string.login,
                variant = "TEXT"
            )
        )

        Spacer(modifier = Modifier.padding(20.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                text = passwordState,
                idPlaceholder = R.string.password,
                variant = "PASSWORD"
            )
        )

        Spacer(modifier = Modifier.padding(20.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                text = confirmPassword,
                idPlaceholder = R.string.confirm_password,
                variant = "PASSWORD"
            )
        )

        Button(
            onClick = {
                goToMain.invoke(loginState.value, passwordState.value, confirmPassword.value)
            },
            modifier = Modifier
                .padding(top = 150.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = DevAgro
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
            loginState = remember { mutableStateOf("") },
            passwordState = remember { mutableStateOf("") },
            confirmPassword = remember { mutableStateOf("") },
            goToMain = { _,_,_ -> }
        )

    }
}