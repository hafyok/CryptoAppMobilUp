package com.example.cryptoapp.Presentation.DetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptoapp.Presentation.ErrorContent
import com.example.cryptoapp.Presentation.LoadingScreen
import com.example.cryptoapp.ui.theme.LightGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    isLoading: Boolean,
    isError: Boolean,
    title: String,
    onRetry: () -> Unit,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    //.shadow(4.dp)
            ) {
                TopAppBar(
                    title = {
                        Text(
                            title,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint = LightGrey
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                HorizontalDivider(modifier = Modifier.shadow(2.dp))
            }
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                when {
                    isLoading -> LoadingScreen()
                    isError -> ErrorContent(onRetry = onRetry)
                    else -> {
                        // Здесь будет основной контент
                    }
                }
            }
        }
    )
}

@Composable
@Preview
fun PreviewDetailScreen() {
    DetailScreen(isLoading = false, isError = true, "Bitcoin", onRetry = {}, navigateBack = {})
}