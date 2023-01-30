package com.example.cryptocurrency.data.remote.dto

import com.example.cryptocurrency.domain.module.Coin

data class CoinDto(
    val id: String = "",
    val is_active: Boolean = false,
    val is_new: Boolean = false,
    val name: String = "",
    val rank: Int = 0,
    val symbol: String = "",
    val type: String = ""
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        is_active = is_active,
        rank = rank,
        symbol = symbol

    )
}