package com.example.ecommerce.presention.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.databinding.RcvItemProductBinding
import com.example.ecommerce.databinding.SliderItemProductBinding
import com.example.ecommerce.domain.model.Product
import javax.inject.Inject

class SliderImageAdapter @Inject constructor(private val mIListenClick: ListProductAdapter.IListenClick):RecyclerView.Adapter<SliderImageAdapter.SliderImageViewHolder>() {
    private var listProduct:MutableList<Product>? = null

    fun setData(list: List<Product>){
        listProduct = list as MutableList<Product>
        notifyDataSetChanged()
    }

    inner class SliderImageViewHolder(private val sliderItemProductBinding: SliderItemProductBinding) :
        RecyclerView.ViewHolder(sliderItemProductBinding.root) {
        fun onBind(product: Product?){
            Glide.with(sliderItemProductBinding.root)
                .load("https://shop-ec-pro.herokuapp.com${product?.image}")
                .into(sliderItemProductBinding.imgVpProduct)
            sliderItemProductBinding.imgVpProduct.setOnClickListener {
                if (product != null) {
                    mIListenClick.listenClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val sliderItemProductBinding = SliderItemProductBinding.inflate(inflater,parent,false)
        return SliderImageViewHolder(sliderItemProductBinding)
    }

    override fun onBindViewHolder(holder: SliderImageViewHolder, position: Int) {
        holder.onBind(listProduct?.get(position))
    }

    override fun getItemCount(): Int = 3
    interface IListenClick {
        fun listenClick(product: Product)
    }
}