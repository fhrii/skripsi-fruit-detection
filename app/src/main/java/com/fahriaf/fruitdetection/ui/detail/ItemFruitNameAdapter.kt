package com.fahriaf.fruitdetection.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahriaf.fruitdetection.databinding.ItemFruitNameBinding

class ItemFruitNameAdapter : RecyclerView.Adapter<ItemFruitNameAdapter.ViewHolder>() {
    private val fruitNames = ArrayList<String>()

    fun setFruitNames(fruitNames: List<String>) {
        this.fruitNames.clear()
        this.fruitNames.addAll(fruitNames)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFruitNameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(fruitNames[position])

    override fun getItemCount() = fruitNames.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFruitNameBinding.bind(itemView)

        fun bind(data: String) {
            binding.btnFruitName.text = data
        }
    }
}