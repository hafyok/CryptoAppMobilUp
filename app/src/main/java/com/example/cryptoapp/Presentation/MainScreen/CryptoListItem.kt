package com.example.cryptoapp.Presentation.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptoapp.R
import com.example.cryptoapp.ui.theme.DarkGrey
import com.example.cryptoapp.ui.theme.LightGrey

@Composable
fun CryptoListItem(
    id_icon: Int,
    description: String,
    title: String,
    price: Double,
    percent: Double,
    currency: String,
    //TODO() После подключения API добавить знаки
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row {
                Image(
                    painter = painterResource(id = id_icon),
                    contentDescription = description,
                    modifier = Modifier.padding(8.dp)
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
            id_icon = R.drawable.ic_btc,
            description = "BTC",
            title = "Bitcoin",
            price = 28560.95,
            percent = 4.05,
            currency = "$"
        )
        CryptoListItem(
            id_icon = R.drawable.ic_eth,
            description = "ETC",
            title = "Etherium",
            price = 2600.74,
            percent = 2.14,
            currency = "$"
        )
        CryptoListItem(
            id_icon = R.drawable.ic_bnb,
            description = "BNB",
            title = "Binance",
            price = 432.69,
            percent = 14.05,
            currency = "$"
        )
    }

}
