package com.example.ecommerce.presention.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.databinding.RcvItemProductBinding
import com.example.ecommerce.databinding.RcvItemReviewsBinding
import com.example.ecommerce.domain.model.Product
import com.example.ecommerce.domain.model.Reviews


class ListReviewAdapter() : RecyclerView.Adapter<ListReviewAdapter.ListReviewViewHolder>() {
    private var listReviews: MutableList<Reviews>? = null
    fun setData(list: List<Reviews>?) {
        listReviews = list as MutableList<Reviews>
        notifyDataSetChanged()
    }

    inner class ListReviewViewHolder(private val rcvItemReviewsBinding: RcvItemReviewsBinding) :
        RecyclerView.ViewHolder(rcvItemReviewsBinding.root) {
        fun onBind(reviews: Reviews?) {
            rcvItemReviewsBinding.apply {
                tvNameReviewer.text = reviews?.name
                starReviewer.setNumber(reviews?.rating?:0)
                tvCreateReview.text = reviews?.createdAt?.substring(0,10)
                tvContentReview.text = reviews?.comment
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val rcvItemReviewsBinding = RcvItemReviewsBinding.inflate(inflater, parent, false)
        return ListReviewViewHolder(rcvItemReviewsBinding)
    }

    override fun onBindViewHolder(holder: ListReviewViewHolder, position: Int) {
        holder.onBind(listReviews?.get(position))
    }

    override fun getItemCount(): Int = listReviews?.size ?: 0
}