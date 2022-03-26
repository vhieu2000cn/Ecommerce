package com.example.ecommerce.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Orders(

	@field:SerializedName("itemsPrice")
	val itemsPrice: String? = null,

	@field:SerializedName("shippingPrice")
	val shippingPrice: String? = null,

	@field:SerializedName("totalPrice")
	val totalPrice: String? = null,

	@field:SerializedName("shippingAddress")
	val shippingAddress: ShippingAddress? = null,

	@field:SerializedName("paymentMethod")
	val paymentMethod: String? = null,

	@field:SerializedName("taxPrice")
	val taxPrice: String? = null,

	@field:SerializedName("orderItems")
	val orderItems: List<OrderItemsItem?>? = null
) : Parcelable



