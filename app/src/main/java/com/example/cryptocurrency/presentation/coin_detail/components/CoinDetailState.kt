package com.example.cryptocurrency.presentation.coin_detail.components

import com.example.cryptocurrency.domain.module.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
