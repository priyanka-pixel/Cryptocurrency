package com.example.cryptocurrency.data.remote.dto

data class LinksExtended(
    val stats: Stats = Stats(),
    val type: String = "",
    val url: String = ""
)