package com.veldan.test_gifgallery.gif_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veldan.test_gifgallery.R
import com.veldan.test_gifgallery.databinding.ItemGifListBinding

/**
 *  Using [DiffUtils] for more efficient list work.
 *
 * */
class GifListDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.equals(newItem)
    }
}

class GifListAdapter :
    ListAdapter<String, GifListAdapter.GifItemViewHolder>(GifListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifItemViewHolder {
        return GifItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GifItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /**
     * Gif Item View Holder
     *
     * @constructor (private) - because the value [imageView] is obtained as a result of calling the method [from()]
     * in the [onCreateViewHolder] class, you don't need to pass the constructor parameter anywhere else.
     *
     * */
    class GifItemViewHolder
    private constructor(
        private val binding: ItemGifListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.root

        companion object {
            fun from(parent: ViewGroup): GifItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemGifListBinding.inflate(layoutInflater, parent, false)
                return GifItemViewHolder(binding)
            }
        }

        fun bind(gifUrl: String) {
            binding.also {
                it.gifUrl = gifUrl
                it.executePendingBindings()
            }

        }
    }
}