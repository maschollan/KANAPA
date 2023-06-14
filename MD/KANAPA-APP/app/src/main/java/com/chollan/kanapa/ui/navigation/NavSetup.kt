package com.chollan.kanapa.ui.navigation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chollan.kanapa.helper.uriToFile
import com.chollan.kanapa.model.AuthUser
import com.chollan.kanapa.ui.component.AboutScreen
import com.chollan.kanapa.ui.component.AuthScreen
import com.chollan.kanapa.ui.component.HomeScreen
import com.chollan.kanapa.ui.component.NearestScreen
import com.chollan.kanapa.ui.component.ProfileScreen
import com.chollan.kanapa.ui.component.ResultScreen
import com.chollan.kanapa.ui.component.SplashScreen
import com.chollan.kanapa.viewmodel.AuthPreferences
import com.chollan.kanapa.viewmodel.AuthViewModel
import com.chollan.kanapa.viewmodel.AuthViewModelFactory
import com.chollan.kanapa.viewmodel.MainViewModel
import com.chollan.kanapa.viewmodel.MainViewModelFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody


@Composable
fun NavSetup(navController: NavHostController, modifier: Modifier = Modifier) {
    var imageResult by rememberSaveable { mutableStateOf(Uri.EMPTY) }
    var cameraHasTake by rememberSaveable { mutableStateOf(false) }
    var nextAuth by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val mainViewModel: MainViewModel = viewModel(factory = MainViewModelFactory())
    val authViewModel: AuthViewModel =
        viewModel(factory = AuthViewModelFactory(AuthPreferences(context)))

    val isLoading by mainViewModel.isLoading
    val postPredict by mainViewModel.postPredict
    val postRegister by mainViewModel.postRegister
    val postLogin by mainViewModel.postLogin
    val authUser by authViewModel.authUser
        .collectAsState(initial = AuthUser("", "", ""))

    val launcherIntentGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        if (it.resultCode != RESULT_OK) {
            return@rememberLauncherForActivityResult
        }

        imageResult = it.data?.data as Uri
        val imageFile = uriToFile(imageResult, context)

        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file", imageFile.name, requestImageFile
        )
        mainViewModel.predict(imageMultipart)
        navController.navigate(Screen.Result.route)
    }

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Result.route) {
            ResultScreen(modifier = modifier,
                navController = navController,
                imageResult = imageResult,
                postPredict = postPredict,
                isLoading = isLoading,
                onFindNearest = {
                    nextAuth = "nearest"
                    if (authUser.token == "") navController.navigate(Screen.Login.route)
                    else navController.navigate(Screen.Nearest.route)
                })
        }
        composable(Screen.Home.route) {
            HomeScreen(modifier = modifier.padding(top = 24.dp),
                navController = navController,
                onImageCaptured = {
                    imageResult = it
                    cameraHasTake = true
                },
                onGalleryClick = {
                    val intent = Intent()
                    intent.action = Intent.ACTION_GET_CONTENT
                    intent.type = "image/*"
                    val chooser = Intent.createChooser(intent, "Choose a Picture")
                    launcherIntentGallery.launch(chooser)
                },
                onProfileClick = {
                    nextAuth = "profile"
                    if (authUser.token == "") navController.navigate(Screen.Login.route)
                    else navController.navigate(Screen.Profile.route)
                },
                hasTakeCamera = cameraHasTake,
                resetCamera = {
                    val imageFile = uriToFile(imageResult, context)

                    val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
                    val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "file", imageFile.name, requestImageFile
                    )
                    mainViewModel.predict(imageMultipart)
                    cameraHasTake = false
                })
        }
        composable(Screen.Splash.route) {
            SplashScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Nearest.route) {
            NearestScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                modifier = modifier.padding(top = 24.dp),
                navController = navController,
                authUser = authUser,
                onLogout = {
                    authViewModel.saveAuthUser(
                        AuthUser(
                            "", "", ""
                        )
                    )
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                })
        }
        composable(Screen.About.route) {
            AboutScreen(modifier = modifier.padding(top = 24.dp), navController = navController)
        }
        composable(Screen.Login.route) {
            var email by rememberSaveable { mutableStateOf("") }
            var next by rememberSaveable { mutableStateOf("") }

            if (postLogin.message == "success") {
                authViewModel.saveAuthUser(
                    AuthUser(
                        postLogin.loginResult.name, email, postLogin.loginResult.token
                    )
                )
                mainViewModel.reset()
                navController.popBackStack()
                next = nextAuth
                nextAuth = ""
                when (next) {
                    "nearest" -> navController.navigate(Screen.Nearest.route)
                    "profile" -> navController.navigate(Screen.Profile.route)
                    else -> navController.navigate(Screen.Home.route)
                }
            }
            AuthScreen(
                isLoading, onPostClick = { _, _email, _password ->
                    mainViewModel.login(_email, _password)
                    email = _email
                }, modifier = modifier.padding(top = 24.dp), navController = navController
            )
        }
        composable(Screen.Register.route) {
            if (postRegister.message == "User Created") {
                mainViewModel.reset()
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            }
            AuthScreen(
                isLoading,
                onPostClick = { name, email, password ->
                    mainViewModel.register(name, email, password)
                },
                modifier = modifier.padding(top = 24.dp),
                navController = navController,
                isLogin = false
            )
        }
    }
}