package com.example.ecommerce.presention.ui.page

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentProductBinding
import com.example.ecommerce.domain.model.Product
import com.example.ecommerce.domain.model.ShoppingCart
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.adapter.ListReviewAdapter
import com.example.ecommerce.presention.ui.viewmodel.ProductViewModel
import com.example.ecommerce.presention.ui.viewmodel.SplashViewModel


class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>() {
    override val layoutId: Int = R.layout.fragment_product
    override val viewModel: ProductViewModel by activityViewModels()
    private val navArgs: ProductFragmentArgs by navArgs()

    private val splashViewModel: SplashViewModel by activityViewModels()

    override fun onReady(savedInstanceState: Bundle?) {
        initData(navArgs.product)
        viewModel.getProduct(navArgs.product)

    }

    override fun onStart() {
        super.onStart()
        viewModel.getShoppingCart()
        viewModel.getNumProduct()
    }

    private fun initData(product: Product?) {
        if (splashViewModel.logged.value != null){
            binding.tvReview.visibility = View.GONE
            binding.layoutReview.visibility = View.VISIBLE
            binding.btSmReview.visibility = View.VISIBLE
        }
        binding.apply {
            btBack.setOnClickListener {
                viewModel.navigateBack()
            }
            btShoppingCart.setOnClickListener {
                viewModel.navigate(ProductFragmentDirections.actionProductFragmentToShoppingCartFragment())
            }
            btAddtocard.setOnClickListener {
                if(product?.countInStock?:0 !=0){
                    viewModel.navigate(ProductFragmentDirections.actionProductFragmentToShoppingCartFragment())
                    viewModel.insertShoppingCartToDB(
                        viewModel.productNumber.value?.let { it1 ->
                            ShoppingCart(null, it1, product!!)
                        }
                    )
                }else{
                    Toast.makeText(requireContext(), "Không thể mua", Toast.LENGTH_LONG).show()
                }
            }
            btMinusProduct.setOnClickListener {
                if (!viewModel.minusProduct()) {
                    Toast.makeText(requireContext(), "Không thể trừ thêm", Toast.LENGTH_LONG).show()
                }
            }
            btPlusProduct.setOnClickListener {
                if (!viewModel.plusProduct()) {
                    Toast.makeText(requireContext(), "Không thể cộng thêm", Toast.LENGTH_LONG)
                        .show()
                }
            }
            viewModel.productNumber.observe(viewLifecycleOwner) {
                tvNumProduct.text = it.toString()
            }
            productTitle.text = product?.name
            productPrice.text = "$${product?.price}"
            Glide.with(binding.root)
                .load("https://shop-ec-pro.herokuapp.com${product?.image}")
                .placeholder(R.drawable.product_sample)
                .into(binding.productImg)
            productRattingStar.setNumber(product?.rating ?: 0)
            if (product?.countInStock ?: 0 > 0) {
                productStatus.text = "Status: In stock"
            } else {
                productStatus.text = "Status: Out Of Stock"
            }
            binding.productRattingNumber.text = "${product?.reviews?.size} Review"
            binding.productDescription.text = "Description: ${product?.description}"
        }

        val adapter = ListReviewAdapter()
        adapter.setData(product?.reviews)
        binding.productRcv.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setAdapter(adapter)
        }
    }
}