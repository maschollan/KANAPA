package com.chollan.kanapa.ui.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
    var imageResult by remember { mutableStateOf(Uri.EMPTY) }
    var cameraHasTake by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Result.route) {
            ResultScreen(
                modifier = modifier,
                navController = navController,
                imageResult = imageResult
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                modifier = modifier.padding(top = 24.dp),
                navController = navController,
                onImageCaptured = {
                    imageResult = it
                    cameraHasTake = true
                },
                hasTakeCamera = cameraHasTake,
                resetCamera = {
                    cameraHasTake = false
                }
            )
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
            AuthScreen(
                modifier = modifier.padding(top = 24.dp),
                navController = navController,
                isLogin = false
            )
        }
    }
}