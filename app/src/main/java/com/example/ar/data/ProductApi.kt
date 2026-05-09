package com.example.ar.data

import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ApiProduct>
}