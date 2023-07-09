package com.eva.androidweatherapp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(controller: NavController): T {
    val current = destination.parent?.route ?: return koinViewModel()
    // destination parent route if not there then there is no nested route
    // thus return the base viewModel
    // otherwise get the current backstack entry of the parent and save thus by remember
    val parent = remember(this) {
        controller.getBackStackEntry(current)
    }
    return koinViewModel(viewModelStoreOwner = parent)
}