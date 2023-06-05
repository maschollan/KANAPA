package com.chollan.kanapa.ui.component

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chollan.kanapa.MainActivity
import com.chollan.kanapa.R
import com.chollan.kanapa.helper.distanceTo
import com.chollan.kanapa.helper.getDistance
import com.chollan.kanapa.helper.toLatLng
import com.chollan.kanapa.model.DataKanapa
import com.chollan.kanapa.model.LocationDetails
import com.chollan.kanapa.model.NearStore
import com.chollan.kanapa.ui.theme.KANAPATheme
import com.chollan.kanapa.ui.theme.Reset
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun NearestItem(nearest: NearStore, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier) {
            Text(
                text = nearest.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 2.dp)
            )
            Text(
                text = nearest.address,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )
            Text(
                text = nearest.subtitle,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier.align(Alignment.TopEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = nearest.distance.getDistance(),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(end = 2.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
                contentDescription = "detail",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun NearestScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val listState = rememberLazyListState()
    val nearestList = DataKanapa.storeList
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(nearestList[0].toLatLng(), 11f)
    }

    val context = LocalContext.current

    val uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }

    val markerClick: (Marker) -> Boolean = {
        cameraPositionState.projection?.let {}
        false
    }

    val coroutineScope = rememberCoroutineScope()

    var currentLocation by remember {
        mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
    }
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                val oldLocation = currentLocation
                currentLocation = LocationDetails(lo.latitude, lo.longitude)

                val newCameraPosition =
                    CameraPosition.fromLatLngZoom(
                        LatLng(
                            currentLocation.latitude,
                            currentLocation.longitude
                        ), 12f
                    )

                if (currentLocation.distanceTo(oldLocation) > 10) coroutineScope.launch {
                    cameraPositionState.animate(
                        CameraUpdateFactory.newCameraPosition(
                            newCameraPosition
                        ), 1000
                    )
                }
            }
        }
    }

    LaunchedEffect(true) {
        locationCallback.let {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }


    Reset(MaterialTheme.colorScheme)

    Column(modifier = modifier) {
        TopBar(navController = navController, isBack = true)

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            properties = properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState
        ) {
            nearestList.forEach {
                Marker(
                    state = MarkerState(position = LatLng(it.lat.toDouble(), it.lon.toDouble())),
                    title = it.name,
                    snippet = it.subtitle,
                    onClick = markerClick
                )
            }
        }

        Text(
            text = "Toko Cupang Terdekat",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )


        LazyColumn(state = listState) {
            itemsIndexed(nearestList) { index, nearest ->
                NearestItem(nearest = nearest, Modifier.clickable {
                    val newCameraPosition =
                        CameraPosition.fromLatLngZoom(nearest.toLatLng(), 14f)

                    coroutineScope.launch {
                        cameraPositionState.animate(
                            CameraUpdateFactory.newCameraPosition(
                                newCameraPosition
                            ), 1000
                        )
                    }
                })
                if (index < nearestList.lastIndex)
                    Divider(color = MaterialTheme.colorScheme.inverseOnSurface)
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNearestScreen() {
    KANAPATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
            NearestScreen()
        }
    }
}