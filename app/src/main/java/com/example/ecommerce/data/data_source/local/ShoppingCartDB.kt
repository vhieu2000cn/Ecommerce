package com.example.ecommerce.data.data_source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ecommerce.data.data_source.model.ShoppingCartEntity
import com.example.ecommerce.data.data_source.model.util.ConverterShoppingCartEntity

@Database(entities = [ShoppingCartEntity::class], version = 1)
@TypeConverters(ConverterShoppingCartEntity::class)
abstract class ShoppingCartDB : RoomDatabase() {
    abstract fun shoppingCartDao(): ShoppingCartDao
    companion object {
        @Volatile
        private var instance: ShoppingCartDB? = null
        fun getInstance(context: Context): ShoppingCartDB {
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    ShoppingCartDB::class.java,
                    "ShoppingCart"
                ).build()
            }
            return instance!!
        }
    }
}