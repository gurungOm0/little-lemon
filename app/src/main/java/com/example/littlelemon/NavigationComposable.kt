package com.example.littlelemon

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavigationComposable(sharedPreferences: SharedPreferences,dbFetch:List<MenuItem>) {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
    val userLoggedIn = sharedPref.getBoolean("userLogged",false)
    val startDes = if (userLoggedIn) HomeDes.route else OnboardingDes.route

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDes) {

        composable(HomeDes.route){
            Home(navController,dbFetch)
        }

        composable(OnboardingDes.route){
            Onboarding(navController,sharedPreferences)
        }

        composable(ProfileDes.route){
            Profile(navController)
        }
    }
}