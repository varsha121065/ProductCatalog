package com.varsha.productcatalog.data

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String,
    val images: List<String>
)