package com.veldan.test_gifgallery.gif_list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veldan.test_gifgallery.databinding.ItemGifListBinding
import com.veldan.test_gifgallery.network.Images

/**
 *  Using [DiffUtils] for more efficient list work.
 *
 * */
class GifListDiffCallback : DiffUtil.ItemCallback<Images>() {

    override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem.fixedHeight == newItem.fixedHeight
                && oldItem.fixedWidth == newItem.fixedWidth
    }
}

class GifListAdapter(val clickListener: GifItemListener):
    ListAdapter<Images, GifListAdapter.GifItemViewHolder>(GifListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifItemViewHolder {
        return GifItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GifItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
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

        fun bind(gifImage: Images, clickListener: GifItemListener) {
            binding.also {
                it.gifImage = gifImage
                it.clickListener = clickListener
                it.executePendingBindings()
            }

        }
    }
}

class GifItemListener(val clickListener: (url: String) -> Unit) {
    fun onClick(gifImage: Images) {
        clickListener(gifImage.fixedHeight.url)
    }
}