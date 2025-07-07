package com.example.littlelemon.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.local.MenuItem
import com.example.littlelemon.screens.home.Home
import com.example.littlelemon.screens.home.HomeViewModel
import com.example.littlelemon.screens.onboarding.OnBoardingViewModel
import com.example.littlelemon.screens.onboarding.Onboarding
import com.example.littlelemon.screens.profile.Profile
import com.example.littlelemon.screens.profile.ProfileViewModel


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavigationComposable() {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
    val userLoggedIn = sharedPref.getBoolean("userLogged", false)
    val startDes = if (userLoggedIn) HomeDes.route else OnboardingDes.route

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDes) {

        composable(HomeDes.route) {
            val vm: HomeViewModel = hiltViewModel()
            Home(navController, vm.items)
        }

        composable(OnboardingDes.route) {
            val vm: OnBoardingViewModel = hiltViewModel()
            Onboarding(navController, vm.fName,vm.lName,vm.email,{vm.fName = it.toString()},{vm.lName = it.toString()},{ vm.email = it.toString() },
                { vm.submit() })
        }

        composable(ProfileDes.route) {
            val vm: ProfileViewModel = hiltViewModel()
            LaunchedEffect(Unit) { vm.load() }
            Profile(navController,vm.profile!!) { vm.logout() }
        }
    }
}