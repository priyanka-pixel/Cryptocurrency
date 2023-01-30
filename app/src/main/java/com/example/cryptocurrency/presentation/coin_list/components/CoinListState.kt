package com.example.cryptocurrency.presentation.coin_list.components

import com.example.cryptocurrency.domain.module.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
