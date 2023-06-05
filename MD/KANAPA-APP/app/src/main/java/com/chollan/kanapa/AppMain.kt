package com.chollan.kanapa

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.ui.component.HomeScreen
import com.chollan.kanapa.ui.component.NearestScreen
import com.chollan.kanapa.ui.component.ResultScreen
import com.chollan.kanapa.ui.component.SplashScreen
import com.chollan.kanapa.ui.navigation.NavSetup
import com.chollan.kanapa.ui.theme.KANAPATheme
import com.chollan.kanapa.ui.theme.Reset


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMain(navController: NavHostController = rememberNavController()) {
    KANAPATheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Reset(MaterialTheme.colorScheme)
            NavSetup(navController)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewApp() {
    AppMain()
}