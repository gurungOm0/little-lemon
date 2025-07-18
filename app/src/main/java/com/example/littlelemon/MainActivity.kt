package com.example.littlelemon


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.littlelemon.navigation.NavigationComposable
import com.example.littlelemon.ui.theme.LittleLemonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        Log.i("Log", "Running")
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = true // for dark icons on light background
        controller.isAppearanceLightNavigationBars = true
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars
        fun Context.isDarkThemeEnabled(): Boolean {
            return (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        }

        val isDark = isDarkThemeEnabled()
        enableEdgeToEdge(
            statusBarStyle = if(isDark){
                SystemBarStyle.dark(scrim = Color.Black.toArgb())
            } else {
                SystemBarStyle.light(scrim = Color.White.toArgb(), darkScrim = Color.Black.toArgb())
            },
            navigationBarStyle = if(isDark){
                SystemBarStyle.dark(scrim = Color.Transparent.toArgb())
            } else {
                SystemBarStyle.light(scrim = Color.White.toArgb(), darkScrim = Color.Black.toArgb())
            }
        )

        setContent {
            LittleLemonTheme {
//                val windowSize = calculateWindowSizeClass(this)
                NavigationComposable()
            }
        }
    }
}