package com.example.ecommerce.presention.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce.databinding.RcvItemProductBinding
import com.example.ecommerce.databinding.RcvItemProductOrderBinding
import com.example.ecommerce.domain.model.OrderItemsItem
import com.example.ecommerce.domain.model.Product

class ListOrderAdapter: RecyclerView.Adapter<ListOrderAdapter.ListOrderViewHolder>() {
    private var listProduct: MutableList<OrderItemsItem>? = null
    fun setData(list: List<OrderItemsItem>) {
        listProduct = list as MutableList<OrderItemsItem>
        notifyDataSetChanged()
    }
    inner class ListOrderViewHolder(private val rcvItemProductOrderBinding: RcvItemProductOrderBinding) :
        RecyclerView.ViewHolder(rcvItemProductOrderBinding.root){
            fun onBind(get: OrderItemsItem?) {
                rcvItemProductOrderBinding.apply {
                    tvItemOrderTitle.text = get?.name
                    tvItemOrderPrice.text = "${get?.qty}x$${get?.price}"
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rcvItemProductOrderBinding = RcvItemProductOrderBinding.inflate(inflater, parent, false)
        return ListOrderViewHolder(rcvItemProductOrderBinding)
    }

    override fun onBindViewHolder(holder: ListOrderViewHolder, position: Int) {
        holder.onBind(listProduct?.get(position))
    }

    override fun getItemCount(): Int = listProduct?.size?:0
}