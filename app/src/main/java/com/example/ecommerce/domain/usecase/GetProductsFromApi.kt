package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.model.Products
import com.example.ecommerce.domain.repository.EcommerceRepository
import javax.inject.Inject

class GetProductsFromApi @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
    suspend operator fun invoke():Products? = ecommerceRepository.getProductsFromApi()
}