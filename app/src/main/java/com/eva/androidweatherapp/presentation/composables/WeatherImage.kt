package com.eva.androidweatherapp.presentation.composables

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R

@Composable
fun WeatherImage(
    @DrawableRes res: Int,
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.primaryContainer,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    imagePadding: PaddingValues = PaddingValues(10.dp)
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = res),
            contentDescription = "Current Day weather",
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .padding(imagePadding)
        )
    }
}

@Preview
@Composable
fun WeatherImagePreview() {
    WeatherImage(res = R.drawable.ic_sleet)
}