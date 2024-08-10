package com.example.cryptoapp.Presentation.DetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cryptoapp.R

@Composable
fun DetailCrypto(image: String, describeText: String, categories: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
        Text(
            text = stringResource(id = R.string.describe),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(text = describeText)

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = stringResource(id = R.string.categories),
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Text(text = categories)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDetailCrypto() {
    DetailCrypto(
        image = "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
        describeText = "Bitcoin is a decentralized cryptocurrency originally described in a 2008 whitepaper by a person, or group of people, using the alias Satoshi Nakamoto. It was launched soon after, in January 2009.\n" +
                "\n" +
                "Bitcoin is a peer-to-peer online currency, meaning that all transactions happen directly between equal, independent network participants, without the need for any intermediary to permit or facilitate them. Bitcoin was created, according to Nakamoto’s own words, to allow “online payments to be sent directly from one party to another without going through a financial institution.”\n" +
                "Bitcoin is a peer-to-peer online currency, meaning that all transactions happen directly between equal, independent network participants, without the need for any intermediary to permit or facilitate them. Bitcoin was created, according to Nakamoto’s own words, to allow “online payments to be sent directly from one party to another without going through a financial institution.”",
        categories = "Smart Contract Platform, Ethereum Ecosystems"
    )
}