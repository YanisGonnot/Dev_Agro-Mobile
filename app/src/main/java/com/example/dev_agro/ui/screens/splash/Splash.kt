package com.example.dev_agro.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.R
import com.example.dev_agro.logic.SplashViewModel
import com.example.dev_agro.ui.theme.GreenBg
import com.example.dev_agro.utils.APP_NAME


@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel){
    SplashView()

    LaunchedEffect(Unit) {
        viewModel.goToMainScreenSharedFlow.collect {
            val destination = if (it)
                Screen.Dashboard.route
            else {
                Screen.Login.route
            }
            navController.navigate(destination) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigateAfterDelay()
    }
}



@Composable
fun SplashView(){
    Column(
        modifier = Modifier
            .background(GreenBg)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = APP_NAME
        )

        Text(
            text = APP_NAME,
            color = Color.White,
            fontSize = 30.sp
        )
    }
}



@Preview(showBackground = true)
@Composable
fun SplashPreview(){
    SplashView()

}