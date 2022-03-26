package com.example.ecommerce.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("countInStock")
    val countInStock: Int,

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("numReviews")
    val numReviews: Int,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("reviews")
    val reviews: List<Reviews>,

    @field:SerializedName("price")
    val price: Double,

    @field:SerializedName("__v")
    val V: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("_id")
    val id: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("brand")
    val brand: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
) : Parcelable
