package com.example.ecommerce.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingCart(
    @field:SerializedName("id")
    var id:Int?,
    @field:SerializedName("qualityProduct")
    var qualityProduct: Int,
    @field:SerializedName("product")
    val product: Product
): Parcelable
