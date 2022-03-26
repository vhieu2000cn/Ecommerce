package com.example.ecommerce.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(

    @field:SerializedName("pages")
    val pages: Int,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("products")
    val products: List<Product>
) : Parcelable
