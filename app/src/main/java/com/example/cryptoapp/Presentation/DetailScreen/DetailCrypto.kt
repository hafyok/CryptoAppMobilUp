package com.example.cryptoapp.Presentation.DetailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cryptoapp.R

@Composable
fun DetailCrypto(image: String, describeText: String, categories: String) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .padding(8.dp)
                )
            }
        }

        item {
            Text(
                text = stringResource(id = R.string.describe),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        item { Spacer(modifier = Modifier.padding(vertical = 8.dp)) }

        item { Text(text = describeText) }

        item { Spacer(modifier = Modifier.padding(vertical = 8.dp)) }

        item {
            Text(
                text = stringResource(id = R.string.categories),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        item { Spacer(modifier = Modifier.padding(vertical = 8.dp)) }

        item { Text(text = categories) }
    }
}
