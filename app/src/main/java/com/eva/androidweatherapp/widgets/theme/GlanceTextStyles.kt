package com.eva.androidweatherapp.widgets.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceComposable
import androidx.glance.GlanceTheme
import androidx.glance.text.FontWeight
import androidx.glance.text.TextStyle

object GlanceTextStyles {

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun extraLargeTextStyleWithOnSurfaceVariantColor() = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = GlanceTheme.colors.onSurfaceVariant
    )

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun largeTextStyleWithOnSurfaceVariantColor() = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = GlanceTheme.colors.onSurfaceVariant
    )


    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun mediumTextStyleWithOnSurfaceVariantColor() = TextStyle(
        color = GlanceTheme.colors.onSurfaceVariant,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun smallTextStyleWithOnSurfaceVariantColor() = TextStyle(
        color = GlanceTheme.colors.onSurfaceVariant,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun smallTextStyleWithSecondaryColor() = TextStyle(
        color = GlanceTheme.colors.secondary,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun smallTextStyleWithOnPrimaryContainerColor() = TextStyle(
        color = GlanceTheme.colors.onSurfaceVariant,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun extraSmallTextStyleWithOnPrimaryContainerColor() = TextStyle(
        color = GlanceTheme.colors.onSurfaceVariant,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    )

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun smallTitleTextStyle() = TextStyle(
        color = GlanceTheme.colors.onSurfaceVariant,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    @Composable
    @GlanceComposable
    @ReadOnlyComposable
    fun smallSubTitleTextStyle() = TextStyle(
        color = GlanceTheme.colors.onSurfaceVariant,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
}