package com.varsha.productcatalog.data

class ProductRepository(
    private val api: ProductApi
) {

    suspend fun getProducts(
        limit: Int,
        skip: Int
    ): ProductResponse {
        return api.getProducts(
            limit = limit,
            skip = skip
        )
    }
}