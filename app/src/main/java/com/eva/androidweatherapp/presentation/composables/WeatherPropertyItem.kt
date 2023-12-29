package com.eva.androidweatherapp.presentation.composables

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.presentation.util.WeatherPropertyStyle
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@Composable
fun WeatherPropertyItem(
    @DrawableRes image: Int,
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    style: WeatherPropertyStyle = WeatherPropertyStyle.ROW_STACKED,
    background: Color = MaterialTheme.colorScheme.secondaryContainer,
    onBackground: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    titleStyle: TextStyle = MaterialTheme.typography.labelLarge,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    when (style) {
        WeatherPropertyStyle.ROW_STACKED -> Row(
            modifier = modifier
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            WeatherImage(
                res = image,
                onBackGround = onBackground,
                background = background,
                modifier = Modifier.size(40.dp),
                iPadding = PaddingValues(4.dp),
                shape = MaterialTheme.shapes.small,
                contentDescription = title
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(text = title, style = titleStyle, color = titleColor)
                Text(text = value, style = textStyle, color = textColor)
            }
        }

        WeatherPropertyStyle.COLUMN_LINEAR -> Column(
            modifier = modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            WeatherImage(
                res = image,
                onBackGround = onBackground,
                background = background,
                modifier = Modifier.size(40.dp),
                iPadding = PaddingValues(4.dp),
                shape = MaterialTheme.shapes.small,
                contentDescription = title
            )
            Text(text = title, style = titleStyle, color = titleColor)
            Text(text = value, style = textStyle, color = textColor)
        }
    }
}

private class WeatherPropertyItemStylePreviewParams :
    CollectionPreviewParameterProvider<WeatherPropertyStyle>(
        listOf(WeatherPropertyStyle.ROW_STACKED, WeatherPropertyStyle.COLUMN_LINEAR)
    )

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherPropertyItemPreview(
    @PreviewParameter(WeatherPropertyItemStylePreviewParams::class)
    style: WeatherPropertyStyle
) = AndroidWeatherAppTheme {
    WeatherPropertyItem(
        image = R.drawable.ic_sunrise,
        title = "Sunrise",
        value = "5:00AM",
        style = style
    )
}