package com.Exsample.epicmart.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RemoteViews.RemoteCollectionItems
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.Exsample.epicmart.Model.SliderModel
import com.Exsample.epicmart.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions

class SliderAdapter(
private var sliderItems:List<SliderModel>,
    private val viewPager2: ViewPager2

):RecyclerView.Adapter<SliderAdapter.SliderViewHolder> () {

  private lateinit var context: Context
  private val runnable= Runnable {
      sliderItems=sliderItems
      notifyDataSetChanged()
  }

    class SliderViewHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
private val imageview:ImageView=itemView.findViewById(R.id.imageSlide)

fun setImage(sliderItems:SliderModel,context: Context) {
    val requestOptions = RequestOptions().transform(CenterInside())

    Glide.with(context)
        .load(sliderItems.url)
        .apply(requestOptions)
        .into(imageview)

}

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {

        context=parent.context
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_image_container,parent,false)
        return SliderViewHolder(view)

    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
    holder.setImage(sliderItems[position],context)
    if(position==sliderItems.lastIndex-1){
        viewPager2.post(runnable)
    }
    }

    override fun getItemCount(): Int =sliderItems.size
}