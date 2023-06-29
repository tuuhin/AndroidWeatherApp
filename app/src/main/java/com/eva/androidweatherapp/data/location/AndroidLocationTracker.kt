package com.eva.androidweatherapp.data.location

import android.Manifest
import android.content.Context
import android.location.LocationManager
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.getSystemService
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.models.BaseLocationModel
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class AndroidLocationTracker(
    private val context: Context,
    private val locationProviderClient: FusedLocationProviderClient,
) : LocationTracker() {

    private val locationService by lazy { context.getSystemService<LocationManager>() }

    override val isGpsEnabled: Boolean
        get() = locationService?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

    override val isLocationEnabled: Boolean
        get() = locationService?.isLocationEnabled ?: false

    override val isNetworkProviderEnabled: Boolean
        get() = locationService?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentLocation(): BaseLocationModel? {
        val coarseLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED

        val fineLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED


        if (!coarseLocationPermissions || !fineLocationPermissions ||
            !isLocationEnabled || (!isNetworkProviderEnabled && !isGpsEnabled)
        ) return null


        return suspendCancellableCoroutine { cancellableContinuation ->
            locationProviderClient.lastLocation.apply {

                addOnSuccessListener {
                    cancellableContinuation.resume(
                        BaseLocationModel(
                            latitude = result.latitude, longitude = result.longitude
                        ), onCancellation = null
                    )
                }
                addOnFailureListener {
                    cancellableContinuation.resume(null, onCancellation = null)
                }
                addOnCanceledListener {
                    cancellableContinuation.cancel()
                }
            }
        }
    }
}