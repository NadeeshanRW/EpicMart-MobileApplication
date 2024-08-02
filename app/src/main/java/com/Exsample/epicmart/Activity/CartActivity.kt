package com.Exsample.epicmart.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.Exsample.epicmart.Adapter.CardAdapter
import com.Exsample.epicmart.Helper.ChangeNumberItemsListener
import com.Exsample.epicmart.R
import com.Exsample.epicmart.databinding.ActivityCartBinding
import com.example.project1762.Helper.ManagmentCart

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        setVariable()
        initCartList()
        calculateCart()
    }

    private fun calculateCart() {
        val parcentTax=0.02
        val dilivary=15.0
        tax=Math.round((managmentCart.getTotalFee()*parcentTax)*100) /100.0
        val total =Math.round((managmentCart.getTotalFee()+tax+dilivary)*100)/100
        val itemTotal =Math.round(managmentCart.getTotalFee()*100)/100

        with(binding){
            totalFeeTxt.text ="$$itemTotal"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$dilivary"
            totalTxt.text="$$total"
        }
    }

    private fun initCartList() {
        binding.cartView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter=CardAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }
        })

    }


    private fun setVariable(){
        binding.backBtn.setOnClickListener{finish()}

    }
}