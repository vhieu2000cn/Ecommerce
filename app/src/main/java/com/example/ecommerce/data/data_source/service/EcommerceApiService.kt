package com.example.ecommerce.data.data_source.service

import com.example.ecommerce.domain.model.*
import retrofit2.http.*


interface EcommerceApiService {
    @GET("products")
    suspend fun getProductsFromApi(): Products?

    @POST("users/login")
    suspend fun login(
        @Body requestBD: SingInBody
    ): SingIn?

    @POST("users")
    suspend fun singUp(
        @Body requestBD: SingUpBody
    ): SingIn?
    @POST("orders")
    suspend fun order(
        @Body orders: Orders,
        @Header("Authorization") authHeader: String
    ): Order?

}