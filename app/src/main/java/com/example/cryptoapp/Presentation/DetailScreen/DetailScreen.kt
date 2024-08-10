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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptoapp.Presentation.ErrorContent
import com.example.cryptoapp.Presentation.LoadingScreen
import com.example.cryptoapp.ui.theme.Grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    isLoading: Boolean,
    isError: Boolean,
    title: String,
    onRetry: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel
) {
    val coinDetail by viewModel.coinDetail.collectAsState()
    val state by viewModel.state.collectAsState()

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
                                tint = Grey
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
                    state.isLoading -> LoadingScreen()
                    //state.isError -> ErrorContent(onRetry = { viewModel.retry() })
                    else -> {
                        // Здесь будет основной контент
                        DetailCrypto(
                            image = coinDetail.image?.large.toString(),
                            describeText = "Bitcoin is a decentralized cryptocurrency originally described in a 2008 whitepaper by a person, or group of people, using the alias Satoshi Nakamoto. It was launched soon after, in January 2009.\n" +
                                    "\n" +
                                    "Bitcoin is a peer-to-peer online currency, meaning that all transactions happen directly between equal, independent network participants, without the need for any intermediary to permit or facilitate them. Bitcoin was created, according to Nakamoto’s own words, to allow “online payments to be sent directly from one party to another without going through a financial institution.”\n" +
                                    "Bitcoin is a peer-to-peer online currency, meaning that all transactions happen directly between equal, independent network participants, without the need for any intermediary to permit or facilitate them. Bitcoin was created, according to Nakamoto’s own words, to allow “online payments to be sent directly from one party to another without going through a financial institution.”",
                            categories = "Smart Contract Platform, Ethereum Ecosystems"
                        )
                    }
                }
            }
        }
    )
}


/*
@Composable
@Preview
fun PreviewDetailScreen() {
    DetailScreen(isLoading = false, isError = true, "Bitcoin", onRetry = {}, navigateBack = {})
}*/
