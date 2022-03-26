package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.repository.EcommerceRepository
import javax.inject.Inject

class SaveTokenToDS @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
    suspend operator fun invoke(token: String) = ecommerceRepository.saveToken(token)
}