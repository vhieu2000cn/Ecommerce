package com.example.ecommerce.data.data_source.model.util

import androidx.room.TypeConverter
import com.example.ecommerce.data.data_source.model.ShoppingCartEntity
import com.example.ecommerce.domain.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterShoppingCartEntity {
    @TypeConverter
    fun toShoppingCartEntity(product: String): Product? {
        //val listType = object : TypeToken<ArrayList<Product>>(){}.type
        return Gson().fromJson<Product>(product,Product::class.java)
    }

    @TypeConverter
    fun fromShoppingCartEntity(product:Product?): String? {
        return Gson().toJson(product)
    }
}