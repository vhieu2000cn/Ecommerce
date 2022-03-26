package com.example.ecommerce.domain.usecase

import com.example.ecommerce.domain.model.SingIn
import com.example.ecommerce.domain.repository.EcommerceRepository
import javax.inject.Inject

class SingUpToApi @Inject constructor(private val ecommerceRepository: EcommerceRepository) {
    suspend operator fun invoke(name: String,email: String, pass: String): SingIn? = ecommerceRepository.singUpToApi(name,email,pass)
}