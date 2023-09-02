package com.eva.androidweatherapp.widgets.composables

import android.content.Context
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
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.action.Action
import androidx.glance.action.clickable
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
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles
import com.eva.androidweatherapp.widgets.utils.AvailableSizes
import com.eva.androidweatherapp.widgets.utils.roundedCorners
import com.eva.androidweatherapp.widgets.utils.toWidgetSize

@Composable
@GlanceComposable
fun WeatherErrorLayout(
    error: String,
    action: Action,
    modifier: GlanceModifier = GlanceModifier,
    size: DpSize = LocalSize.current,
    context: Context = LocalContext.current
) {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_refresh_symbol),
                contentDescription = context.getString(R.string.refresh),
                modifier = GlanceModifier
                    .size(20.dp)
                    .clickable(action),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
            )

            when (size.toWidgetSize()) {
                AvailableSizes.WIDGET_SIZE_SMALL -> Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.ic_error),
                        contentDescription = context.getString(R.string.no_weather_data),
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                        modifier = GlanceModifier.padding(4.dp)
                            .size(50.dp)
                            .roundedCorners(
                                color = GlanceTheme.colors.primaryContainer,
                                resId = R.drawable.shape_rounded_container
                            ),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = GlanceModifier.width(10.dp))
                    Column(
                        modifier = GlanceModifier.defaultWeight()
                    ) {
                        Text(
                            text = context.getString(R.string.error_text),
                            style = GlanceTextStyles.smallTextStyleWithOnPrimaryContainerColor(),
                            modifier = GlanceModifier.padding(vertical = 2.dp)
                        )
                        Text(
                            text = error,
                            style = GlanceTextStyles.extraSmallTextStyleWithOnPrimaryContainerColor(),
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
                        provider = ImageProvider(R.drawable.ic_error),
                        contentDescription = context.getString(R.string.no_weather_data),
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                        modifier = GlanceModifier.padding(4.dp)
                            .size(50.dp)
                            .roundedCorners(
                                color = GlanceTheme.colors.primaryContainer,
                                resId = R.drawable.shape_rounded_container
                            ),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = GlanceModifier.height(10.dp))
                    Text(
                        text = context.getString(R.string.error_text),
                        style = TextStyle(
                            color = GlanceTheme.colors.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ), modifier = GlanceModifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = error,
                        style = GlanceTextStyles.smallTextStyleWithOnPrimaryContainerColor(),
                        maxLines = 2
                    )
                }
            }
        }
    }
}