package com.chollan.kanapa.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.R
import com.chollan.kanapa.ui.theme.KANAPATheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TopBar(
            navController = navController,
            isBack = true,
            showLogo = false,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(
                    if (isSystemInDarkTheme()) R.drawable.kanapa_logo_square_putih
                    else R.drawable.kanapa_logo_square
                ),
                contentDescription = "app_logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(174.dp)
            )
            Text(
                text = stringResource(R.string.bangkit_capstone_project),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = stringResource(R.string.versi_app),
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAboutScreen() {
    KANAPATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            AboutScreen()
        }
    }
}