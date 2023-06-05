package com.chollan.kanapa.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chollan.kanapa.ui.component.AboutScreen
import com.chollan.kanapa.ui.component.AuthScreen
import com.chollan.kanapa.ui.component.HomeScreen
import com.chollan.kanapa.ui.component.NearestScreen
import com.chollan.kanapa.ui.component.ProfileScreen
import com.chollan.kanapa.ui.component.ResultScreen
import com.chollan.kanapa.ui.component.SplashScreen

@Composable
fun NavSetup(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Splash.route ) {
        composable(Screen.Result.route) {
            ResultScreen(modifier = modifier, navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Splash.route) {
            SplashScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Nearest.route) {
            NearestScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.About.route) {
            AboutScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Login.route) {
            AuthScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Register.route) {
            AuthScreen(modifier = modifier.padding(top = 24.dp), navController = navController, isLogin = false)
        }
    }
}