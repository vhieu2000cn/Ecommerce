package com.example.ecommerce.presention.ui.page

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.domain.model.Product
import com.example.ecommerce.presention.base.BaseFragment
import com.example.ecommerce.presention.ui.adapter.ListProductAdapter
import com.example.ecommerce.presention.ui.adapter.SliderImageAdapter
import com.example.ecommerce.presention.ui.viewmodel.HomeViewModel
import com.example.ecommerce.presention.ui.viewmodel.SplashViewModel
import com.example.ecommerce.util.Status

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by activityViewModels()

    private val splashViewModel: SplashViewModel by activityViewModels()

    override fun onReady(savedInstanceState: Bundle?) {
        viewModel.getProductsFromApi()
        initData()
        initSlider()
    }

    override fun onResume() {
        binding.linearLayout2.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.alpha))
        binding.linearLayout3.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.down_to_up))
        super.onResume()
        binding.shimmerViewContainer1.startShimmer()
        binding.shimmerViewContainer2.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerViewContainer1.stopShimmer()
        binding.shimmerViewContainer2.stopShimmer()

    }

    private fun initData() {
        val adapter = ListProductAdapter(viewModel)
        binding.rcvProduct.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            setAdapter(adapter)

        }
        viewModel.products.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                binding.shimmerViewContainer1.stopShimmer()
                binding.shimmerViewContainer1.visibility = View.GONE
                binding.shimmerViewContainer2.stopShimmer()
                binding.shimmerViewContainer2.visibility = View.GONE
                adapter.setData(it.data!!.products)
            } else {
                viewModel.getProductsFromApi()
            }
        }
        binding.btShoppingCart.setOnClickListener {
            viewModel.navigate(HomeFragmentDirections.actionHomeFragmentToShoppingCartFragment())
        }
        binding.btLogin.setOnClickListener {
            if (splashViewModel.logged.value != null){
                viewModel.navigate(HomeFragmentDirections.actionHomeFragmentToLogOutFragment())
            }else{
                viewModel.navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        }
    }

    private fun initSlider() {
        val adapter = SliderImageAdapter(viewModel)
        viewModel.products.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                adapter.setData(it.data!!.products.take(3))
            }

        }
        binding.vp2Slider.adapter = adapter
        binding.cirSlider.setViewPager(binding.vp2Slider)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onStatusBar() {
        val window = activity?.window
        window?.statusBarColor = ContextCompat.getColor(requireContext(),R.color.main)
        window?.insetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
    }
}