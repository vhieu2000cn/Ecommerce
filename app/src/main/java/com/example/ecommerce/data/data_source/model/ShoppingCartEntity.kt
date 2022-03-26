package com.example.ecommerce.data.data_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerce.domain.model.Product

@Entity(tableName = "ShoppingCart")
data class ShoppingCartEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val qualityProduct: Int,
    var product: Product
)