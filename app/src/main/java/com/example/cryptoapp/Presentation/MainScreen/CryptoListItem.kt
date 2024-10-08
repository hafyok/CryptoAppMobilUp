package com.example.cryptoapp.Presentation.MainScreen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cryptoapp.ui.theme.DarkGrey
import com.example.cryptoapp.ui.theme.Grey
import com.example.cryptoapp.ui.theme.LightGrey
import com.example.cryptoapp.ui.theme.LightRed
import java.util.Locale

@Composable
fun CryptoListItem(
    icon: String,
    description: String,
    title: String,
    price: String,
    percent: String,
    currency: String,
    id: String, // Добавьте параметр id валюты
    onClick: (String) -> Unit // Функция, принимающая id
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(id) }, // Передаем id при нажатии
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
                        text = description.uppercase(),
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
                text = if (percent.startsWith("-")) "- ${percent.removePrefix("-")}%"
                else "+ $percent%", //эти махинации нужны, чтобы между минусом и цифрами был пробел
                color = if (percent.startsWith("-")) LightRed else Grey,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
