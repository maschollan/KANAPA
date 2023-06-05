package com.chollan.kanapa.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.chollan.kanapa.ui.navigation.Screen
import com.chollan.kanapa.ui.theme.KANAPATheme
import com.chollan.kanapa.ui.theme.Reset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    isLogin: Boolean = true
) {
    Reset(MaterialTheme.colorScheme)
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            Image(
                painter = painterResource(
                    if (isSystemInDarkTheme()) R.drawable.kanapa_logo_land_putih
                    else R.drawable.kanapa_logo_land
                ),
                contentDescription = "app_logo",
                modifier = Modifier
                    .width(230.dp)
                    .padding(vertical = 32.dp)
                    .align(CenterHorizontally)
            )
            Text(
                text = stringResource(if (isLogin) R.string.login else R.string.register),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (!isLogin) Row(modifier = Modifier.padding(bottom = 16.dp)) {
                TextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.inverseSurface
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            stringResource(R.string.name),
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.inverseSurface,
                            RoundedCornerShape(32.dp)
                        )
                )
            }

            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                TextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.inverseSurface
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            stringResource(R.string.email),
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.inverseSurface,
                            RoundedCornerShape(32.dp)
                        )
                )
            }

            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                TextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.inverseSurface
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            stringResource(R.string.password),
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.inverseSurface,
                            RoundedCornerShape(32.dp)
                        )
                )
            }

            Row {
                Text(
                    text = stringResource(if (isLogin) R.string.havent_account else R.string.have_account),
                    fontSize = 16.sp,
                )
                Text(
                    text = stringResource(if (isLogin) R.string.register else R.string.login),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable {
                            navController.popBackStack()
                            if (isLogin) navController.navigate(Screen.Register.route)
                            else navController.navigate(Screen.Login.route)
                        }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Button(onClick = {
                navController.popBackStack()
                if (isLogin) navController.navigate(Screen.Nearest.route)
                else navController.navigate(Screen.Login.route)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(if (isLogin) R.string.login else R.string.register).uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLoginScreen() {
    KANAPATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            AuthScreen(isLogin = false)
        }
    }
}