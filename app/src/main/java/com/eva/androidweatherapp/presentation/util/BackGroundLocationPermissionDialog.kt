package com.eva.androidweatherapp.presentation.util

import android.Manifest
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.eva.androidweatherapp.R

@Composable
fun requestBackgroundLocationDialog(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
): Boolean {

    var checkPermission by remember {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q)
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) == PermissionChecker.PERMISSION_GRANTED
            )
        else mutableStateOf(true)
    }

    var openDialog by remember { mutableStateOf(true) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { results ->
            Log.d("RESULTS", results.toString())
            checkPermission = results
        }
    )
    if ((!checkPermission && openDialog) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)

        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = { Text(text = stringResource(id = R.string.background_location_title)) },
            text = { Text(text = stringResource(id = R.string.background_location_body)) },
            confirmButton = {
                Button(onClick = { launcher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION) }) {
                    Text(text = "Allow")
                }
            }, dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text(text = "Cancel")
                }
            },
            modifier = modifier
        )
    return checkPermission
}