package com.Exsample.epicmart.Activity

import android.media.Image
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.Exsample.epicmart.Adapter.CategoryAdapter
import com.Exsample.epicmart.Adapter.SliderAdapter
import com.Exsample.epicmart.Model.CategoryModel
import com.Exsample.epicmart.Model.SliderModel
import com.Exsample.epicmart.R
import com.Exsample.epicmart.ViewModel.MainViewModel
import com.Exsample.epicmart.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {


    private val viewModel=MainViewModel()
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    initBanner()
    initCategories()

    }

    private fun initCategories() {
       binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.Viewcategory.layoutManager =
                LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.Viewcategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }

    private fun initBanner() {
        binding.progressBarbannre.visibility = View.VISIBLE
        viewModel.banner.observe(this, Observer {
        banners(it)
        binding.progressBarbannre.visibility =View.GONE
        })
        viewModel.loadBanners()

    }

    private fun banners(image: List<SliderModel>){
        binding.viewPagerSlider.adapter=SliderAdapter(image,binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding=false
        binding.viewPagerSlider.clipChildren=false
        binding.viewPagerSlider.offscreenPageLimit=3
        binding.viewPagerSlider.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if(image.size>1){
            binding.dotIndicater.visibility = View.VISIBLE
            binding.dotIndicater.attachTo(binding.viewPagerSlider)
        }
    }
}