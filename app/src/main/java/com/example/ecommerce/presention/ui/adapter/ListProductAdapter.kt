package com.example.ecommerce.presention.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.databinding.RcvItemProductBinding
import com.example.ecommerce.domain.model.Product
import org.greenrobot.eventbus.EventBus

class ListProductAdapter(
    private val mIListenClick: IListenClick
) : RecyclerView.Adapter<ListProductAdapter.ListProductViewHolder>() {
    private var listProduct: MutableList<Product>? = null
    fun setData(list: List<Product>) {
        listProduct = list as MutableList<Product>
        notifyDataSetChanged()
    }

    inner class ListProductViewHolder(private val rcvItemProductBinding: RcvItemProductBinding) :
        RecyclerView.ViewHolder(rcvItemProductBinding.root) {
        fun onBind(product: Product?) {
            Glide.with(rcvItemProductBinding.root)
                .load("https://shop-ec-pro.herokuapp.com${product?.image}")
                .placeholder(R.drawable.product_sample)
                .into(rcvItemProductBinding.rcvImgProduct)
            rcvItemProductBinding.apply {
                rcvTvTitleProduct.text = product?.name
                rcvTvReviews.text = "${product?.numReviews} Reviews"
                rcvTvPrice.text = "$" + product?.price
                rcvNumberStar.setNumber(product?.rating ?: 0)
                itemProduct.setOnClickListener {
                    product?.let { product ->
                        mIListenClick.listenClick(product)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rcvItemProductBinding = RcvItemProductBinding.inflate(inflater, parent, false)
        return ListProductViewHolder(rcvItemProductBinding)
    }

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        holder.onBind(listProduct?.get(position))
    }

    override fun getItemCount(): Int = listProduct?.size ?: 0
    interface IListenClick {
        fun listenClick(product: Product)
    }
}