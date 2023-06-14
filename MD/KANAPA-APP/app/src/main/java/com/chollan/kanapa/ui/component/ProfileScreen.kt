package com.chollan.kanapa.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.R
import com.chollan.kanapa.model.AuthUser
import com.chollan.kanapa.model.DataKanapa
import com.chollan.kanapa.ui.navigation.Screen
import com.chollan.kanapa.ui.theme.KANAPATheme

@Composable
fun ProfileScreen(
    authUser: AuthUser,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val historyDetect = DataKanapa.historyList
    val listState = rememberLazyGridState()
    val showHistory by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        TopBar(navController = navController, isBack = true)
        Text(
            text = authUser.name,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )
        Text(
            text = authUser.email,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        Divider(color = MaterialTheme.colorScheme.inverseOnSurface, thickness = 8.dp)
        Text(
            text = "Profile",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clickable { onLogout() }
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Logout Akun",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "Pastikan anda mengingat password untuk masuk lagi",
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
                contentDescription = "detail",
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
        Divider(color = MaterialTheme.colorScheme.inverseOnSurface)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .clickable { navController.navigate(Screen.About.route) }
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "About Aplikasi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
                contentDescription = "detail",
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
        if (showHistory) Divider(
            color = MaterialTheme.colorScheme.inverseOnSurface,
            thickness = 8.dp
        )
        if (showHistory) Text(
            text = "Riwayat",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )
        if (showHistory) LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = listState
        ) {
            items(historyDetect, key = { it.name }) { fishDetect ->
                Column {
                    Image(
                        painter = painterResource(id = fishDetect.image),
                        contentDescription = fishDetect.name,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Text(
                        text = fishDetect.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(bottom = 2.dp, top = 8.dp)
                    )
                    Text(
                        text = "Kemiripan ${fishDetect.similarity}%",
                        textAlign = TextAlign.Justify,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewProfileScreen() {
    KANAPATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            ProfileScreen(AuthUser("Ahmad Aldi Prayitno", "aldi@mail.com", ""),{})
        }
    }
}