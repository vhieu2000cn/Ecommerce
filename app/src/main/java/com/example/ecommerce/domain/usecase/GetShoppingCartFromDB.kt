package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.repository.EcommerceRepository
import javax.inject.Inject

class GetShoppingCartFromDB @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
     operator fun invoke() = ecommerceRepository.getShopingCart()
}