package com.example.dev_agro.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dev_agro.logic.LoginViewModel
import com.example.dev_agro.logic.SplashViewModel
import com.example.dev_agro.ui.screens.splash.SplashScreen
import com.example.dev_agro.ui.screens.auth.LoginScreen
import com.example.dev_agro.ui.screens.auth.RegisterScreen
import com.example.dev_agro.ui.screens.farm.FarmScreen
import com.example.dev_agro.ui.screens.onboarding.OnBoardingScreen
import com.example.dev_agro.ui.theme.Dev_AgroTheme

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(Screen.Splash.route) {
            val splashViewModel : SplashViewModel = hiltViewModel()
            SplashScreen(navController, splashViewModel)
        }

        composable(Screen.Login.route) {
            val loginViewModel : LoginViewModel = hiltViewModel()
            LoginScreen(navController, loginViewModel)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(navController)
        }

        composable(Screen.Farm.route) {
            FarmScreen(navController)
        }
        /*
        composable(Screen.Dashboard.route) {
            val dashboardViewModel : DashboardViewModel = hiltViewModel()
            DashboardScreen(navController, dashboardViewModel)
        }

        composable(Screen.Profile.route) {
            val profileViewModel : ProfileViewModel = hiltViewModel()
            ProfileScreen(navController, profileViewModel)
        }

        composable(Screen.Product.route) {
            val farmViewModel : ProductViewModel = hiltViewModel()
            ProductScreen(navController, farmViewModel)
        }
         */
    }
}

sealed class Screen(val route : String){
    object Splash : Screen("Splash")
    object Login : Screen("Login")
    object Register : Screen("Register")
    object Dashboard : Screen("Main")
    object Profile : Screen("Profile")
    object Farm : Screen("Farm")
    object Product : Screen ("Products")
    object ProductInfo : Screen("Info_Product")
    object MyFarm : Screen("MyFarm")
    object OnBoarding: Screen("OnBoarding")
}