package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextDecoration
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.utils.AvailableSizes

@Composable
@GlanceComposable
fun WeatherErrorLayout(
    errorMessage: String,
    modifier: GlanceModifier = GlanceModifier,
    size: DpSize = LocalSize.current,
    action: Action
) {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            Image(
                provider = ImageProvider(R.drawable.ic_refresh_symbol),
                contentDescription = "Refresh Symbol",
                modifier = GlanceModifier
                    .size(20.dp)
                    .clickable(action),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
            )

            when (size) {
                AvailableSizes.WIDGET_SIZE_SMALL -> Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.ic_no_location),
                        contentDescription = "No weather data found",
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.primary),
                        modifier = GlanceModifier.padding(4.dp)
                            .cornerRadius(10.dp)
                            .size(50.dp)
                            .background(GlanceTheme.colors.primaryContainer),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = GlanceModifier.width(10.dp))
                    Column(
                        modifier = GlanceModifier.defaultWeight()
                    ) {
                        Text(
                            text = "Error ",
                            style = TextStyle(
                                color = GlanceTheme.colors.onPrimaryContainer,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            ), modifier = GlanceModifier.padding(vertical = 2.dp)
                        )
                        Text(
                            text = errorMessage,
                            style = TextStyle(
                                color = GlanceTheme.colors.onPrimaryContainer,
                                fontSize = 10.sp
                            ),
                            maxLines = 1
                        )
                    }
                }

                else -> Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.ic_no_location),
                        contentDescription = "No weather data found",
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.primary),
                        modifier = GlanceModifier.padding(4.dp)
                            .cornerRadius(10.dp)
                            .size(60.dp)
                            .background(GlanceTheme.colors.primaryContainer),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = GlanceModifier.height(10.dp))
                    Text(
                        text = "Error Occurred",
                        style = TextStyle(
                            color = GlanceTheme.colors.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline
                        ), modifier = GlanceModifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = errorMessage,
                        style = TextStyle(
                            color = GlanceTheme.colors.onPrimaryContainer,
                            fontSize = 12.sp
                        ),
                        maxLines = 2
                    )
                }
            }
        }
    }
}