package com.chollan.kanapa.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.R
import com.chollan.kanapa.ui.theme.KANAPATheme
import com.chollan.kanapa.ui.theme.Reset

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    isBack: Boolean = false,
    showLogo: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isBack) IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "Localized description"
            )
        } else Box(modifier = Modifier.size(48.dp))
        if (showLogo) Image(
            painter = if (isSystemInDarkTheme()) painterResource(R.drawable.kanapa_logo_land_putih)
            else painterResource(id = R.drawable.kanapa_logo_land),
            contentDescription = "logo",
            modifier = modifier.height(48.dp)
        )
        Box(modifier = Modifier.size(48.dp))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PreviewTopBar() {
    KANAPATheme() {
        TopBar()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PreviewTopBarWithBack() {
    KANAPATheme() {
        TopBar(isBack = true)
    }
}

@Composable
fun ActionTopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                navController.navigateUp() },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0x805D5D5D))
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Localized description",
                tint = Color.White
            )
        }
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0x805D5D5D))
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Localized description",
                tint = Color.White
            )
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PreviewActionBar() {
    KANAPATheme {
        ActionTopBar()
    }
}