package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import com.eva.androidweatherapp.presentation.feature_daily.WeatherGraphType
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun ForecastGraphHeader(
    isDropDownOpen: Boolean,
    selected: WeatherGraphType,
    onToggleGraphState: () -> Unit,
    onGraphTypeChanged: (WeatherGraphType) -> Unit,
    modifier: Modifier = Modifier,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
) {

    var dropDownOffset by remember { mutableStateOf(DpOffset.Zero) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Graph",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Box(
            modifier = Modifier.wrapContentWidth()
        ) {
            TextButton(
                onClick = onToggleGraphState,
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            dropDownOffset =
                                DpOffset(x = offset.x.toDp(), y = offset.y.toDp())
                        }
                    }
            ) {
                Text(
                    text = selected.typeWithUnit(localeAmerican),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
            DropdownMenu(
                expanded = isDropDownOpen,
                onDismissRequest = onToggleGraphState,
                offset = dropDownOffset
            ) {
                WeatherGraphType.entries.forEach { type ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = type.typeWithUnit(localeAmerican),
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        onClick = { onGraphTypeChanged(type) },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }
        }
    }
}