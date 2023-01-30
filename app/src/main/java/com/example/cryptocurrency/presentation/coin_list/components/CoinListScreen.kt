package com.example.cryptocurrency.presentation.coin_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.core.Screen

@Composable
fun CoinListScreen(
    navController: NavController,
    modifier: Modifier,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.coins) { coin ->
                CoinListItem(
                    modifier = modifier.border(BorderStroke(2.dp, Color.Blue)),
                    coin = coin,
                    onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                    }
                )

            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
    


