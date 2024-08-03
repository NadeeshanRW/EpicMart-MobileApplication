package com.Exsample.epicmart.Activity

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel() // Create the notification channel
        managmentCart = ManagmentCart(this)

        getBundle()
        initList()
    }

    private fun initList() {
        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter = SizeListAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageurl in item.picUrl) {
            colorList.add(imageurl)
        }

        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.piclist.adapter = PicListAdapter(colorList, binding.picMain)
        binding.piclist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingText.text = "${item.rating}"

        binding.AddToCart.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertItems(item)

        }

        binding.backBtn.setOnClickListener { finish() }
        binding.CartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CartActivity::class.java))
        }

        Glide.with(this)
            .load(item.sellerpic)
            .apply(RequestOptions().transform(CenterCrop()))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "01"
            val channelName = "Xyz"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Channel for EpicMart notifications"
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }




    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }
}