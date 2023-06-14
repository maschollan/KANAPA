package com.chollan.kanapa.model

data class NearStore(
    val name: String,
    val address: String,
    var distance: Double,
    val subtitle: String,
    val lat: Float,
    val lon: Float,
)