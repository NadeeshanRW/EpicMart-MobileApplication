package com.Exsample.epicmart.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Exsample.epicmart.Model.CategoryModel
import com.Exsample.epicmart.databinding.ViewholderCateoryBinding
import com.bumptech.glide.Glide

class CategoryAdapter (val item:MutableList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
 private lateinit var context: Context

    class Viewholder (val binding:ViewholderCateoryBinding):
    RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
      context=parent.context
        val binding=ViewholderCateoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = item[position]
        holder.binding.title11.text=item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.piccat)


    }

    override fun getItemCount(): Int=item.size
}
