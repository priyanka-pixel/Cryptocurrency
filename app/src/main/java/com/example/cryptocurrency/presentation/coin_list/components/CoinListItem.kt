package com.example.cryptocurrency.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cryptocurrency.domain.module.Coin

@Composable
fun CoinListItem(
    modifier: Modifier,
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClick(coin) }),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.align(CenterVertically)) {
                Text(
                    text = "${coin.rank}. ${coin.name}. ${coin.symbol}",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = coin.type,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = buildString {
//                        append(coin.rank)
//                        append("||")
//                        append(coin.is_new)
//                    },
//                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
//                    color = MaterialTheme.colors.surface,
//                    style = MaterialTheme.typography.caption
//                )
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.End
//                ) {
//                }

            }
            Column {
                Text(
                    text = if (coin.is_active) "Active" else "Inactive",
                    color = if (coin.is_active) Color.Green else Color.Red,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.body2

                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (coin.is_new) "New Coin" else "Old Coin",
                    color = if (coin.is_new) Color.White else Color.White,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.body2

                )

            }
        }
    }
}