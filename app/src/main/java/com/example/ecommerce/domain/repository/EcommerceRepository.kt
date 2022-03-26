package com.example.ecommerce.domain.repository

import com.example.ecommerce.domain.model.*
import kotlinx.coroutines.flow.Flow


interface EcommerceRepository {
    suspend fun getProductsFromApi(): Products?
    suspend fun saveAccount(account: String)
    suspend fun deleteAccount()
    suspend fun getAccount(): Flow<String?>
    suspend fun saveToken(token: String)
    suspend fun getToken(): Flow<String?>
    suspend fun insertShoppingCart(shoppingCart: ShoppingCart)
    fun getShopingCart():Flow<List<ShoppingCart>>
    suspend fun deleteShoppingCart(id: Int)
    suspend fun deleteAllShoppingCart()
    suspend fun updateShoppingCart(shoppingCart: ShoppingCart)
    suspend fun loginToApi(email: String, pass:String): SingIn?
    suspend fun singUpToApi(name: String,email: String, pass:String): SingIn?
    suspend fun orderToApi(orders: Orders,auth: String): Order?
}