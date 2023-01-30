package com.example.cryptocurrency.data.remote.dto

import com.example.cryptocurrency.domain.module.CoinDetail

data class CoinDetailDto(
    val description: String = "",
    val development_status: String = "",
    val first_data_at: String = "",
    val hardware_wallet: Boolean = false,
    val hash_algorithm: String = "",
    val id: String = "",
    val is_active: Boolean = false,
    val is_new: Boolean = false,
    val last_data_at: String = "",
    val links: Links = Links(),
    val links_extended: List<LinksExtended> = listOf(),
    val logo: String = "",
    val message: String = "",
    val name: String = "",
    val open_source: Boolean = false,
    val org_structure: String = "",
    val proof_type: String = "",
    val rank: Int = 0,
    val started_at: String = "",
    val symbol: String = "",
    val tags: List<Tag> = listOf(),
    val team: List<TeamMember> = listOf(),
    val type: String = "",
    val whitepaper: Whitepaper = Whitepaper()
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        id = id,
        name = name,
        description = description,
        logo = logo,
        symbol = symbol,
        rank = rank,
        tags = tags.map { it.name },
        team = team
    )
}