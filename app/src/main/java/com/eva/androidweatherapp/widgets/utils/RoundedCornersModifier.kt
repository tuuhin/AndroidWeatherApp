package com.eva.androidweatherapp.widgets.utils

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.unit.ColorProvider
import com.eva.androidweatherapp.R

fun GlanceModifier.roundedCorners(
    amount: Dp = 10.dp,
    color: ColorProvider = ColorProvider(color = Color.White),
    @DrawableRes resId: Int = R.drawable.shape_rounded_surface
): GlanceModifier {
    val background = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        GlanceModifier
            .cornerRadius(amount)
            .background(color)
    else GlanceModifier.background(ImageProvider(resId = resId))

    return then(background)
}