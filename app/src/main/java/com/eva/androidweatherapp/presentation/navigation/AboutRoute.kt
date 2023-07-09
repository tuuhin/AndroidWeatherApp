package com.eva.androidweatherapp.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.eva.androidweatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutRoute(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    context: Context = LocalContext.current,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "About") },
                navigationIcon = navigationIcon ?: {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            )
        }, containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) { scPadding ->
        Column(
            modifier = modifier
                .padding(scPadding)
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_api_logo),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            contentDescription = "Information",
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "API",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium,
                    )
                }
                TextButton(
                    onClick = {
                        val uri = Uri.parse("https://www.weatherapi.com/docs/")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(context, intent, null)
                    },

                    ) {
                    Text(
                        text = "Learn More.",
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Divider()
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                    ) {
                        append(stringResource(id = R.string.weather_api_url_readable))
                    }
                    withStyle(SpanStyle(fontWeight = FontWeight.Medium)) { append(",") }
                    append(stringResource(id = R.string.weather_api_desc))
                },
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Preview
@Composable
fun AboutRoutePreview() {
    Surface(color = MaterialTheme.colorScheme.inverseOnSurface) {
        AboutRoute(
            navigationIcon = {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        )
    }
}