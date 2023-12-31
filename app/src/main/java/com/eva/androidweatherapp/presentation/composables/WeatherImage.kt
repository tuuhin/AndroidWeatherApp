package com.eva.androidweatherapp.presentation.composables

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@Composable
fun WeatherImage(
    @DrawableRes res: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    background: Color = MaterialTheme.colorScheme.primaryContainer,
    onBackGround: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iPadding: PaddingValues = PaddingValues(10.dp)
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(shape)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = res),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(onBackGround),
            contentScale = contentScale,
            modifier = Modifier.padding(iPadding)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherImagePreview() = AndroidWeatherAppTheme {
    WeatherImage(res = R.drawable.ic_sleet)
}