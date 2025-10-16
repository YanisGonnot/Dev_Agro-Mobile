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
<<<<<<< HEAD
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
=======
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
>>>>>>> 995ff9d (screen login/register & viewModel)
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
<<<<<<< HEAD
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.ui.theme.Green700
=======
import com.example.dev_agro.ui.theme.DevAgro
import com.example.dev_agro.ui.theme.Dev_AgroTheme
>>>>>>> 995ff9d (screen login/register & viewModel)
import com.example.dev_agro.utils.MESSAGE_WELCOME
import com.example.dev_agro.utils.monToast

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel){
    val context = LocalContext.current
<<<<<<< HEAD
    val login = remember { mutableStateOf("" ) }
    val password = remember { mutableStateOf("" ) }
    val confirmPassword = remember { mutableStateOf("" ) }
=======
    val loginState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }
>>>>>>> 995ff9d (screen login/register & viewModel)



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
<<<<<<< HEAD
        login = login,
        password = password,
        confirmPassword = confirmPassword,
=======
        loginState = loginState,
        passwordState = passwordState,
        confirmPassword = confirmPasswordState,
>>>>>>> 995ff9d (screen login/register & viewModel)
        goToMain = { username, password, password2 ->
            viewModel.checkFormAndRegister(username, password, password2)
        }
    )

}


@Composable
<<<<<<< HEAD
fun RegisterContent(login : MutableState<String>,
                    password : MutableState<String>,
=======
fun RegisterContent(loginState : MutableState<String>,
                    passwordState : MutableState<String>,
>>>>>>> 995ff9d (screen login/register & viewModel)
                    confirmPassword : MutableState<String>,
                    goToMain : (String, String, String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        MyTitle(
            idText = R.string.new_account,
<<<<<<< HEAD
            modifier = Modifier.padding(top = 150.dp)
        )

        Spacer(modifier = Modifier.padding(50.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                value = login,
                placeholder = stringResource(R.string.login),
=======
            modifier = Modifier.padding(top = 80.dp)
        )

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                text = loginState,
                idPlaceholder = R.string.login,
>>>>>>> 995ff9d (screen login/register & viewModel)
                variant = "TEXT"
            )
        )

        Spacer(modifier = Modifier.padding(20.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
<<<<<<< HEAD
                value = password,
                placeholder = stringResource(R.string.password),
=======
                text = passwordState,
                idPlaceholder = R.string.password,
>>>>>>> 995ff9d (screen login/register & viewModel)
                variant = "PASSWORD"
            )
        )

        Spacer(modifier = Modifier.padding(20.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
<<<<<<< HEAD
                value = confirmPassword,
                placeholder = stringResource(R.string.confirm_password),
=======
                text = confirmPassword,
                idPlaceholder = R.string.confirm_password,
>>>>>>> 995ff9d (screen login/register & viewModel)
                variant = "PASSWORD"
            )
        )

        Button(
            onClick = {
<<<<<<< HEAD
                goToMain.invoke(login.value, password.value, confirmPassword.value)
=======
                goToMain.invoke(loginState.value, passwordState.value, confirmPassword.value)
>>>>>>> 995ff9d (screen login/register & viewModel)
            },
            modifier = Modifier
                .padding(top = 150.dp),
            colors =  ButtonDefaults.buttonColors(
<<<<<<< HEAD
                containerColor = Green700
=======
                containerColor = DevAgro
>>>>>>> 995ff9d (screen login/register & viewModel)
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
<<<<<<< HEAD
            login = remember { mutableStateOf("" ) },
            password = remember { mutableStateOf("" ) },
            confirmPassword = remember { mutableStateOf("" ) },
=======
            loginState = remember { mutableStateOf("") },
            passwordState = remember { mutableStateOf("") },
            confirmPassword = remember { mutableStateOf("") },
>>>>>>> 995ff9d (screen login/register & viewModel)
            goToMain = { _,_,_ -> }
        )

    }
}