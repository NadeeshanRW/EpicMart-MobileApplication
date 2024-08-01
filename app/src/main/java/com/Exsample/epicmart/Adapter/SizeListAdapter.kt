package com.Exsample.epicmart.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

import com.Exsample.epicmart.R
import androidx.recyclerview.widget.RecyclerView
import com.Exsample.epicmart.databinding.ViewholderPicListBinding
import com.Exsample.epicmart.databinding.ViewholderSizeBinding
import com.bumptech.glide.Glide

class SizeListAdapter(val items:MutableList<String>):
    RecyclerView.Adapter<SizeListAdapter.Viewholder>() {

                private var selectedPosition = -1
                private var lastSelectedPosition = -1
                private lateinit var context: Context


   inner class Viewholder (val binding: ViewholderSizeBinding):
       RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListAdapter.Viewholder {
      context=parent.context
        val binding=ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return  Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.sizeText.text =items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)



        }
        if (selectedPosition==position) {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.purple_background3)
            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int =items.size
}