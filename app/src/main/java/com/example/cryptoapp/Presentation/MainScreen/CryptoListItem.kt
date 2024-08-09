package com.example.cryptoapp.Presentation.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cryptoapp.ui.theme.DarkGrey
import com.example.cryptoapp.ui.theme.LightGrey

@Composable
fun CryptoListItem(
    icon: String,
    description: String,
    title: String,
    price: String,
    percent: String,
    currency: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row {
                AsyncImage(
                    model = icon,
                    contentDescription = description,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(8.dp)
                )
                Column() {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Medium,
                        color = DarkGrey,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = description,
                        color = LightGrey
                    )
                }
            }
        }


        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "$currency $price", fontWeight = FontWeight.Medium, color = DarkGrey)
            Text(
                text = "$percent%",
                color = LightGrey,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCryptoListItem() {
    Column {
        CryptoListItem(
            icon = "https://coin-images.coingecko.com/coins/images/325/large/Tether.png?1696501661",
            description = "btc",
            title = "Bitcoin",
            price = "28560.95",
            percent = "4.05",
            currency = "$"
        )
    }
}
