package com.example.ecommerce.presention.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.databinding.RcvItemShoppingcartBinding
import com.example.ecommerce.databinding.SliderItemProductBinding
import com.example.ecommerce.domain.model.Product
import com.example.ecommerce.domain.model.ShoppingCart

class ListShoppingCartAdapter(private val iListenClick:IListenClick) :
    RecyclerView.Adapter<ListShoppingCartAdapter.ListShoppingCartViewHolder>() {
    private var listShoppingCart: MutableList<ShoppingCart>? = null
    fun setData(list: List<ShoppingCart>) {
        listShoppingCart = list as MutableList<ShoppingCart>
        notifyDataSetChanged()
    }

    inner class ListShoppingCartViewHolder(
        private val rcvItemShoppingcartBinding: RcvItemShoppingcartBinding
    ) : RecyclerView.ViewHolder(rcvItemShoppingcartBinding.root) {
        fun onBind(shoppingCart: ShoppingCart?) {
            Glide.with(rcvItemShoppingcartBinding.root)
                .load("https://shop-ec-pro.herokuapp.com${shoppingCart?.product?.image}")
                .placeholder(R.drawable.product_sample)
                .into(rcvItemShoppingcartBinding.imgRcvSpcProduct)
            rcvItemShoppingcartBinding.tvNumShoppingCart.text =
                shoppingCart?.qualityProduct.toString()
            rcvItemShoppingcartBinding.tvRcvSpcTitle.text = shoppingCart?.product?.name
            rcvItemShoppingcartBinding.tvRcvSpcPrice.text = "$${shoppingCart?.product?.price}"
            if (shoppingCart != null){
                rcvItemShoppingcartBinding.btMinusShoppingCart.setOnClickListener {
                    iListenClick.minusShoppingCart(shoppingCart)
                }
                rcvItemShoppingcartBinding.btPlusShoppingCart.setOnClickListener {
                    iListenClick.plusShoppingCart(shoppingCart)
                }
                rcvItemShoppingcartBinding.btCancelShoppingCart.setOnClickListener {
                    iListenClick.cancelShoppingCart(shoppingCart)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListShoppingCartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rcvItemShoppingcartBinding = RcvItemShoppingcartBinding.inflate(inflater, parent, false)
        return ListShoppingCartViewHolder(rcvItemShoppingcartBinding)
    }

    override fun onBindViewHolder(holder: ListShoppingCartViewHolder, position: Int) {
        holder.onBind(listShoppingCart?.get(position))
    }

    override fun getItemCount(): Int = listShoppingCart?.size ?: 0

    interface IListenClick {
        fun minusShoppingCart(shoppingCart: ShoppingCart)
        fun plusShoppingCart(shoppingCart: ShoppingCart): Boolean
        fun cancelShoppingCart(shoppingCart: ShoppingCart)
    }
}