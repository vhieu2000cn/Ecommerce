package com.example.ecommerce.data.data_source.local

import androidx.room.*
import com.example.ecommerce.data.data_source.model.ShoppingCartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingCartDao {
    @Insert
    suspend fun insertShoppingCart(shoppingCartEntity: ShoppingCartEntity)

    @Query("SELECT * FROM ShoppingCart")
    fun getShoppingCart(): Flow<List<ShoppingCartEntity>>

    @Query("DELETE FROM ShoppingCart WHERE id = :ids")
    suspend fun deleteShoppingCart(ids: Int)

    @Query("DELETE FROM ShoppingCart")
    suspend fun deleteAllShoppingCart()

    @Update
    suspend fun updatePlaylist(shoppingCartEntity: ShoppingCartEntity)
}