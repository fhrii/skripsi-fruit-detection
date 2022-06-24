package com.fahriaf.fruitdetection.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fahriaf.fruitdetection.core.domain.model.Fruit
import com.fahriaf.fruitdetection.databinding.ItemFruitNameBinding

class ItemFruitNameAdapter : RecyclerView.Adapter<ItemFruitNameAdapter.ViewHolder>() {
    private val fruits = ArrayList<Fruit>()

    fun setFruits(fruits: List<Fruit>) {
        this.fruits.clear()
        this.fruits.addAll(fruits)
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
        holder.bind(fruits[position])

    override fun getItemCount() = fruits.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFruitNameBinding.bind(itemView)

        fun bind(data: Fruit) {
            binding.btnFruitName.text = data.name
            binding.btnFruitName.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_FRUIT, data)
                }
                itemView.context.startActivity(intent)
            }
        }
    }
}