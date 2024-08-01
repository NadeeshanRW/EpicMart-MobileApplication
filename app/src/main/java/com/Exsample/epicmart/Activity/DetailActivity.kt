package com.Exsample.epicmart.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Size
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.Exsample.epicmart.Adapter.PicListAdapter
import com.Exsample.epicmart.Adapter.SizeListAdapter
import com.Exsample.epicmart.Model.ItemsModel
import com.Exsample.epicmart.R
import com.Exsample.epicmart.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ManagmentCart

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private  lateinit var item:ItemsModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()
        initList()

    }

    private fun initList() {
        val sizeList =ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter=SizeListAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList =ArrayList<String>()
        for(imageurl in item.picUrl){
            colorList.add(imageurl)
        }

        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.piclist.adapter=PicListAdapter(colorList,binding.picMain)
        binding.piclist.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun getBundle(){

        item =intent.getParcelableExtra("object")!!

        binding.titleTxt.text =item.title
        binding.descriptionTxt.text =item.description
        binding.priceTxt.text ="$" + item.price

        binding.AddToCart.setOnClickListener{
            item.numberInCart = numberOrder
            managmentCart.insertItems(item)

        }

        binding.backBtn.setOnClickListener{finish()}
        binding.CartBtn.setOnClickListener{

        }

        Glide.with(this)
            .load(item.sellerpic)
            .apply(RequestOptions().transform(CenterCrop()))

    }
}