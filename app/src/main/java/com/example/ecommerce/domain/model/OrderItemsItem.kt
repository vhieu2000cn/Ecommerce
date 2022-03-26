package com.example.ecommerce.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderItemsItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("product")
    val product: String? = null,

    @field:SerializedName("countInStock")
    val countInStock: Int? = null,

    @field:SerializedName("price")
    val price: Double? = null,

    @field:SerializedName("qty")
    val qty: Int? = null,

    @field:SerializedName("name")
    val name: String? = null
) : Parcelable
