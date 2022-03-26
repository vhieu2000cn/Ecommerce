package com.example.ecommerce.data.data_source.local

import com.example.ecommerce.data.data_source.model.ShoppingCartEntity
import com.example.ecommerce.domain.model.ShoppingCart
import com.example.ecommerce.domain.repository.EcommerceModelMapper
import javax.inject.Inject

class ShoppingCartMapperImpl @Inject constructor() : EcommerceModelMapper<ShoppingCartEntity, ShoppingCart> {
    override fun fromEntity(from: ShoppingCartEntity): ShoppingCart =
        ShoppingCart(from.id,from.qualityProduct, from.product)

    override fun toEntity(from: ShoppingCart): ShoppingCartEntity =
        ShoppingCartEntity(from.id,from.qualityProduct, from.product)
}