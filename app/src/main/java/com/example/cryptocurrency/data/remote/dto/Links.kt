package com.example.cryptocurrency.data.remote.dto

data class Links(
    val explorer: List<String> = listOf(),
    val facebook: List<String> = listOf(),
    val reddit: List<String> = listOf(),
    val source_code: List<String> = listOf(),
    val website: List<String> = listOf(),
    val youtube: List<String> = listOf()
)