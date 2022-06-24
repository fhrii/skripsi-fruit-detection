package com.fahriaf.fruitdetection.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fahriaf.fruitdetection.core.domain.model.FruitImage
import com.fahriaf.fruitdetection.databinding.ItemFruitPictureBinding

class ItemFruitPictureAdapter : RecyclerView.Adapter<ItemFruitPictureAdapter.ViewHolder>() {
    private val fruitImages = ArrayList<FruitImage>()

    fun setFruitImages(fruitImages: List<FruitImage>) {
        this.fruitImages.clear()
        this.fruitImages.addAll(fruitImages)
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
        holder.bind(fruitImages[position])
    }

    override fun getItemCount(): Int = fruitImages.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFruitPictureBinding.bind(itemView)
        fun bind(imageUrl: FruitImage) {
            binding.run {
                Glide.with(itemView.context)
                    .load(imageUrl.url)
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(24)))
                    .into(ivFruit)
            }
        }
    }
}