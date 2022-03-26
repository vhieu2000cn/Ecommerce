package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.repository.EcommerceRepository
import javax.inject.Inject

class DeleteShoppingCartFromDB @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
    suspend operator fun invoke(id:Int) = ecommerceRepository.deleteShoppingCart(id)
}