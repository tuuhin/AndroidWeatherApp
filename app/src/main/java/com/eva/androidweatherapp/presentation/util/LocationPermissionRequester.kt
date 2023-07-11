package com.eva.androidweatherapp.presentation.util

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

@Composable
fun checkLocationPermissions(
    context: Context = LocalContext.current
): Boolean {

    var coarseLocationPermissions by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PermissionChecker.PERMISSION_GRANTED
        )
    }
    var fineLocationPermissions by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PermissionChecker.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            coarseLocationPermissions =
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)

            fineLocationPermissions =
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)
        }
    )

    LaunchedEffect(Unit) {
        if (!coarseLocationPermissions || !fineLocationPermissions) {
            launcher.launch(
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q)
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    ) else
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
            )
        }
    }

    return coarseLocationPermissions && fineLocationPermissions
}