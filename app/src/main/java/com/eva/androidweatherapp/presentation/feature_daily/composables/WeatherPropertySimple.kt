package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R

@Composable
fun WeatherPropertySimple(
    @DrawableRes image: Int,
    value: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "$value image",
            modifier = Modifier.size(40.dp)
        )
        Text(text = title, style = MaterialTheme.typography.bodySmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPropertySimplePreview() {
    WeatherPropertySimple(
        image = R.drawable.ic_humidity,
        title = "Humidity",
        value = "87%",
        modifier = Modifier.padding(4.dp)
    )
}