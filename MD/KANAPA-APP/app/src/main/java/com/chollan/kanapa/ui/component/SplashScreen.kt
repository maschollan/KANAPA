package com.chollan.kanapa.ui.component

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.R
import com.chollan.kanapa.ui.navigation.Screen
import com.chollan.kanapa.ui.theme.KANAPATheme
import kotlinx.coroutines.delay

@Composable
fun Splash(alpha: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(if (isSystemInDarkTheme())R.drawable.kanapa_logo_square_putih else R.drawable.kanapa_logo_square),
            contentDescription = "img_logo",
            modifier = Modifier
                .fillMaxSize(0.4f)
                .alpha(alpha)
        )
    }
}

@Composable
fun SplashScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var alphaStart by remember {
        mutableStateOf(false)
    }
    val alphaAnim =
        animateFloatAsState(targetValue = if (alphaStart) 1f else 0f, tween(3000))

    LaunchedEffect(key1 = true) {
        alphaStart = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
    Splash(alphaAnim.value, modifier)
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSplashScreen() {
    KANAPATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            val navController = rememberNavController()
            SplashScreen(navController)
        }
    }
}