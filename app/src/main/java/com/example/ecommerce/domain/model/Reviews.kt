package com.example.ecommerce.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reviews(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("comment")
    val comment: String,

    @field:SerializedName("_id")
    val id: String,

    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
) : Parcelable