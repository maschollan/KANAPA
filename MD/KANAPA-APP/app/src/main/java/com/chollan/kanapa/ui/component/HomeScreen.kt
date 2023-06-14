package com.chollan.kanapa.ui.component

import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.ui.camera.CameraView
import com.chollan.kanapa.ui.navigation.Screen
import com.chollan.kanapa.ui.theme.KANAPATheme
import com.chollan.kanapa.ui.theme.Reset
import java.util.concurrent.Executors

@Composable
fun HomeScreen(
    onImageCaptured: (Uri) -> Unit,
    onGalleryClick: () -> Unit,
    onProfileClick: () -> Unit,
    resetCamera: () -> Unit,
    hasTakeCamera: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val cameraExecutor = Executors.newSingleThreadExecutor()
    Reset(MaterialTheme.colorScheme)

    if (hasTakeCamera) LaunchedEffect(true) {
        resetCamera()
        navController.navigate(Screen.Result.route)
    }

    Column(modifier = modifier) {
        TopBar()
        CameraView(
            executor = cameraExecutor,
            onImageCaptured = onImageCaptured,
            onGalleryClick = onGalleryClick,
            onProfileClick = onProfileClick,
            onError = {
                Log.e("KANAPA", "View error:", it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    KANAPATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            HomeScreen({}, {}, {}, {}, false)
        }
    }
}