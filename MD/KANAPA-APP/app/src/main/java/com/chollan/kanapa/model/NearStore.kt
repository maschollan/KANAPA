package com.chollan.kanapa.model

data class NearStore(
    val name: String,
    val address: String,
    val distance: Int,
    val subtitle: String,
    val lat: Float,
    val lon: Float,
)