package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable(sharedPreferences: SharedPreferences) {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
    val userLoggedIn = sharedPref.getBoolean("userLogged",false)
    val startDes = if (userLoggedIn) HomeDes.route else OnboardingDes.route

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDes) {

        composable(HomeDes.route){
            Home(navController)
        }

        composable(OnboardingDes.route){
            Onboarding(navController,sharedPreferences)
        }

        composable(ProfileDes.route){
            Profile(navController)
        }
    }
}