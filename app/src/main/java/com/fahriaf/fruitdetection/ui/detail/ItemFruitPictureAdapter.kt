package com.fahriaf.fruitdetection.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fahriaf.fruitdetection.databinding.ItemFruitPictureBinding

class ItemFruitPictureAdapter : RecyclerView.Adapter<ItemFruitPictureAdapter.ViewHolder>() {
    private val fruitImagesUrl = ArrayList<String>()

    fun setFruitImagesUrl(fruitImagesUrl: List<String>) {
        this.fruitImagesUrl.clear()
        this.fruitImagesUrl.addAll(fruitImagesUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFruitPictureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fruitImagesUrl[position])
    }

    override fun getItemCount(): Int = fruitImagesUrl.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFruitPictureBinding.bind(itemView)
        fun bind(imageUrl: String) {
            binding.run {
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(24)))
                    .into(ivFruit)
            }
        }
    }
}