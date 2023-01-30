package com.example.cryptocurrency.domain.module

data class Coin(
    val id: String = "",
    val is_active: Boolean = false,
    val name: String = "",
    val rank: Int = 0,
    val symbol: String = "",
    val type: String = "",
    val is_new: Boolean = false,

    )
