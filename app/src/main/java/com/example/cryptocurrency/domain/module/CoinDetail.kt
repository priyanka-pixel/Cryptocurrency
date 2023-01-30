package com.example.cryptocurrency.domain.module

import com.example.cryptocurrency.data.remote.dto.*

data class CoinDetail(
    val description: String = "",
    val id: String = "",
    val is_active: Boolean = false,
    val logo: String = "",
    val name: String = "",
    val symbol: String = "",
    val rank: Int = 0,
    val tags: List<String> = listOf(),
    val team: List<TeamMember> = listOf(),
)
