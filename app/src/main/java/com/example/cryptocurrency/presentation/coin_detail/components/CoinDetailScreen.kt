package com.example.cryptocurrency.presentation.coin_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cryptocurrency.R
import com.example.cryptocurrency.domain.module.CoinDetail

@Composable
fun CoinDetailScreen(navController: NavController) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(painterResource(id = R.drawable.ic_messanger), contentDescription ="facebook")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Color.Black,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = Color.Blue
                    )
                }
            )
        },

        content = {
            Detail(navController)
        }
    )
}


@Composable
fun Detail(
    navController: NavController,
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        val state = viewModel.state.value
        Box(
            modifier = Modifier.fillMaxSize()
        )
        {
            state.coin?.let { coin ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp),
                )
                {
                    item {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(8f),
                                color = MaterialTheme.colors.surface
                            )
                            AsyncImage(model = coin.logo, contentDescription = "",
                            )

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = coin.description,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.surface
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.surface
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                        ) {
                            coin.tags.forEach { tag ->
                                Tags(tag = tag)
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Team members",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.surface
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    items(coin.team) { teamMember ->
                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Divider()
                    }
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
}

