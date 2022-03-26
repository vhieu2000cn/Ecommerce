package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.model.ShoppingCart
import com.example.ecommerce.domain.repository.EcommerceRepository
import javax.inject.Inject

class SaveShoppingCartToDB @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
    suspend operator fun invoke(shoppingCart: ShoppingCart) = ecommerceRepository.insertShoppingCart(shoppingCart)
}