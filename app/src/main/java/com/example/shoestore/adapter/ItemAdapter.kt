package com.example.shoestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestore.R
import com.example.shoestore.model.ShoeImage

class ItemAdapter(private val images : List <ShoeImage>): RecyclerView.Adapter<ItemAdapter.ImageViewHolder>(){
    private lateinit var mlistner : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mlistner = listener
    }
       inner class ImageViewHolder(view :View, listener:onItemClickListener): RecyclerView.ViewHolder(view){
            private val img = itemView.findViewById<ImageView>(R.id.imageShoeItem)
            fun bindView(image : ShoeImage){
                img.setImageResource(image.imageModel)
            }
           init {
               itemView.setOnClickListener{
                   listener.onItemClick(images[adapterPosition].imageModel)
               }
           }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shoes_model,parent,false)
        return ImageViewHolder(itemView,mlistner)
    }
    override fun getItemCount(): Int =images.size
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position])

    }

}