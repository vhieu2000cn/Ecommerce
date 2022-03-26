package com.example.ecommerce.domain.repository

interface EcommerceModelMapper<E,M> {
    fun fromEntity(from: E):M
    fun toEntity(from: M): E
}