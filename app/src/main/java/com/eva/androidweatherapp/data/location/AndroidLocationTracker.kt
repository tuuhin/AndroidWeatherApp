package com.eva.androidweatherapp.data.location

import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.getSystemService
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.models.BaseLocationModel
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LastLocationRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine

class AndroidLocationTracker(
    private val context: Context,
    private val locationProviderClient: FusedLocationProviderClient,
) : LocationTracker() {

    private val locationTag = "ANDROID_LOCATION_TAG"

    private val currentTag = "ANDROID_LOCATION_CURRENT"

    private val locationManager by lazy { context.getSystemService<LocationManager>() }

    private val isGpsEnabled: Boolean
        get() = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

    private val isLocationEnabled: Boolean
        get() = locationManager?.isLocationEnabled ?: false

    private val isNetworkProviderEnabled: Boolean
        get() = locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false

    private val lastLocationRequester by lazy {
        LastLocationRequest.Builder()
            .setGranularity(Granularity.GRANULARITY_COARSE)
            .build()
    }

    private val currentLocationRequest by lazy {
        CurrentLocationRequest.Builder()
            .setGranularity(Granularity.GRANULARITY_COARSE)
            .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
            .build()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentLocation(): BaseLocationModel? {
        val coarseLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED


        val fineLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED


        if (!coarseLocationPermissions || !fineLocationPermissions
            || !isLocationEnabled || (!isNetworkProviderEnabled && !isGpsEnabled)
        )
            throw UnsupportedOperationException("Permissions or Location Denied")

        // Not using the currentLocation as its a bit resource intensive which is bad for battery health
        return suspendCancellableCoroutine { cont ->
            locationProviderClient
                .getCurrentLocation(currentLocationRequest, null)
                .apply {
                    addOnCompleteListener {
                        addOnSuccessListener {
                            if (result != null)
                                cont.resume(
                                    BaseLocationModel(
                                        latitude = result.latitude,
                                        longitude = result.longitude
                                    ), onCancellation = null
                                )
                            Log.i(currentTag, "SUCCESS RESULT:${result}")
                        }
                        addOnFailureListener {
                            cont.resume(null, onCancellation = null)
                            Log.i(currentTag, "FAILED")
                        }
                    }
                    addOnCanceledListener {
                        cont.cancel()
                        Log.i(currentTag, "CANCELLED")
                    }
                }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getLastLocation(): BaseLocationModel? {
        val coarseLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED


        val fineLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED


        if (!coarseLocationPermissions || !fineLocationPermissions
            || !isLocationEnabled || (!isNetworkProviderEnabled && !isGpsEnabled)
        )
            throw UnsupportedOperationException("Permissions or Location Denied")

        return suspendCancellableCoroutine { continuation ->
            locationProviderClient
                .getLastLocation(lastLocationRequester).apply {
                    addOnCompleteListener {
                        addOnSuccessListener {
                            // The result value can be null as there is no saved last known location
                            // If this occurs open any apps that update the current location
                            // Example: Open google maps and then restart the app
                            // If it don't work we are fetching the current coarse location
                            if (result != null)
                                continuation.resume(
                                    BaseLocationModel(
                                        latitude = result.latitude,
                                        longitude = result.longitude
                                    ), onCancellation = null
                                )
                            else
                                continuation.resume(null, null)
                            Log.i(locationTag, "SUCCESS RESULT:${result}")
                        }
                        addOnFailureListener {
                            continuation.resume(null, onCancellation = null)
                            Log.i(locationTag, "FAILED")
                        }
                    }
                    addOnCanceledListener {
                        continuation.cancel()
                        Log.i(locationTag, "CANCELLED")
                    }
                }
        }
    }


    override suspend fun getLocationAsFlow(): Flow<BaseLocationModel?> {
        val coarseLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED


        val fineLocationPermissions = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED


        if (!coarseLocationPermissions || !fineLocationPermissions
            || !isLocationEnabled || (!isNetworkProviderEnabled && !isGpsEnabled)
        )
            throw UnsupportedOperationException("Permissions or Location Denied")
        return callbackFlow {

            val locationRequest: LocationRequest =
                LocationRequest.Builder(100L)
                    .setIntervalMillis(1000L)
                    .setPriority(Priority.PRIORITY_LOW_POWER)
                    .build()


            val locationCallBack: LocationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let {
                        trySend(
                            BaseLocationModel(
                                latitude = it.latitude,
                                longitude = it.longitude
                            )
                        )
                    }
                }
            }
            locationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallBack,
                Looper.getMainLooper()
            )

            awaitClose { locationProviderClient.removeLocationUpdates(locationCallBack) }
        }

    }

}
