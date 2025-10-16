package com.example.dev_agro.ui.screens.auth

<<<<<<< HEAD
=======
import androidx.compose.foundation.clickable
>>>>>>> 995ff9d (screen login/register & viewModel)
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
<<<<<<< HEAD
import androidx.compose.material3.TextButton
=======
>>>>>>> 995ff9d (screen login/register & viewModel)
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.graphics.Color
=======
>>>>>>> 995ff9d (screen login/register & viewModel)
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dev_agro.R
import com.example.dev_agro.logic.LoginViewModel
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.ui.common.MyOutlinedTextField
<<<<<<< HEAD
=======
import com.example.dev_agro.ui.theme.DevAgro
>>>>>>> 995ff9d (screen login/register & viewModel)
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.utils.MESSAGE_WELCOME
import com.example.dev_agro.utils.monToast
import  com.example.dev_agro.ui.common.MyTitle
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps
<<<<<<< HEAD
import com.example.dev_agro.ui.theme.Green700
=======
>>>>>>> 995ff9d (screen login/register & viewModel)

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel){
    val context = LocalContext.current
<<<<<<< HEAD
    val login = remember { mutableStateOf("" ) }
    val password = remember { mutableStateOf("") }
=======
    val loginState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }


>>>>>>> 995ff9d (screen login/register & viewModel)

    LaunchedEffect(key1 = Unit) {
        viewModel.messageToShowSharedFloat.collect {
            context.monToast(it)
            if (it.contains(MESSAGE_WELCOME))
                navController.navigate(Screen.Dashboard.route)
        }
    }

    LoginContent(
<<<<<<< HEAD
        loginState = login,
        passwordState = password,
=======
        loginState = loginState,
        passwordState = passwordState,
>>>>>>> 995ff9d (screen login/register & viewModel)
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
<<<<<<< HEAD
            modifier = Modifier.padding(top = 100.dp)
        )

        Spacer(modifier = Modifier.padding(50.dp))

        MyOutlinedTextField(
            OutlinedTextFieldsProps(
                value = loginState,
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
                value = passwordState,
                placeholder = stringResource(R.string.password),
=======
                text = passwordState,
                idPlaceholder = R.string.password,
>>>>>>> 995ff9d (screen login/register & viewModel)
                variant = "PASSWORD"
            )
        )

<<<<<<< HEAD
        TextButton(onClick = goToRegister){
            Text(text = stringResource(R.string.link_to_register))
        }

        Spacer(modifier = Modifier.padding(40.dp))

=======
>>>>>>> 995ff9d (screen login/register & viewModel)
        Button(
            onClick = { onValidate.invoke(loginState.value, passwordState.value) },
            modifier = Modifier
                .padding(top = 150.dp),
            colors =  buttonColors(
<<<<<<< HEAD
                containerColor = Green700
            )
        ) {
            Text(
                text = stringResource(R.string.to_login),
                color = Color.White
            )
        }
=======
                containerColor = DevAgro
            )
        ) {
            Text(text = stringResource(R.string.to_login))
        }
        Text(
            text = stringResource(R.string.link_to_register),
            modifier = Modifier.clickable {
                goToRegister.invoke()
            },
            color = DevAgro
        )
>>>>>>> 995ff9d (screen login/register & viewModel)
    }

}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Dev_AgroTheme {
        LoginContent(
<<<<<<< HEAD
            loginState = remember { mutableStateOf("" ) } ,
            passwordState = remember { mutableStateOf("" ) },
=======
            loginState = remember { mutableStateOf("") },
            passwordState = remember { mutableStateOf("") },
>>>>>>> 995ff9d (screen login/register & viewModel)
            onValidate = {_, _ -> },
            goToRegister = {}
        )
    }
}