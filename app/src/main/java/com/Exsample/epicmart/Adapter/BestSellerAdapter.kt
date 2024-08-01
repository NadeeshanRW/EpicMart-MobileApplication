package com.Exsample.epicmart.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Exsample.epicmart.Activity.DetailActivity
import com.Exsample.epicmart.Model.ItemsModel
import com.Exsample.epicmart.databinding.ViewholderBestselerBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class BestSellerAdapter(val items:MutableList<ItemsModel>):RecyclerView.Adapter<BestSellerAdapter.Viewholder>() {
   private var context:Context?=null
    class Viewholder (val binding: ViewholderBestselerBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellerAdapter.Viewholder {
        context=parent.context
        val binding=
            ViewholderBestselerBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: BestSellerAdapter.Viewholder, position: Int) {

        holder.binding.titletext.text=items[position].title
        holder.binding.placetext.text ="$"+items[position].price.toString()
        holder.binding.ratingtext.text=items[position].rating.toString()

        val requstOption =RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requstOption)
            .into(holder.binding.picbestseller)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object", items[position])
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int =items.size
}