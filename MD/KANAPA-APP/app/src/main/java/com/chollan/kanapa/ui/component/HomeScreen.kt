package com.chollan.kanapa.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.R
import com.chollan.kanapa.ui.navigation.Screen
import com.chollan.kanapa.ui.theme.KANAPATheme
import com.chollan.kanapa.ui.theme.Reset
import com.chollan.kanapa.ui.theme.seed

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Reset(MaterialTheme.colorScheme)
    Column(modifier = modifier) {
        TopBar()
        Box(contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(R.drawable.cupang),
                contentDescription = "kamera",

                modifier = Modifier.aspectRatio(9f / 16f),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(154.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0x001E1E1E), Color(0xFF1E1E1E)
                            )
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_collections_24),
                        contentDescription = "Localized description",
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = { navController.navigate(Screen.Result.route) },
                    modifier = Modifier
                        .size(72.dp)
                        .border(4.dp, seed, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Localized description",
                        tint = seed,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD9D9D9))
                            .padding(8.dp)
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_account_circle_24),
                        contentDescription = "Localized description",
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    KANAPATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            HomeScreen()
        }
    }
}