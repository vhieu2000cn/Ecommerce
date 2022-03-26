package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.repository.EcommerceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenFromDS @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
    suspend operator fun invoke(): Flow<String?> = ecommerceRepository.getToken()
}