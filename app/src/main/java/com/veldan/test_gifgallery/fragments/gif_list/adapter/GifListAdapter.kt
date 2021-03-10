package com.veldan.test_gifgallery.fragments.gif_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veldan.test_gifgallery.databinding.ItemGifListBinding
import com.veldan.test_gifgallery.fragments.gif_list.adapter.GifListAdapter.GifItemViewHolder
import com.veldan.test_gifgallery.network.Images

/**
 *  Using [DiffUtils] for more efficient list work.
 *
 * */
class GifListDiffCallback : DiffUtil.ItemCallback<Images>() {

    override fun areItemsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Images, newItem: Images): Boolean {
        return oldItem.fixedHeight == newItem.fixedHeight
                && oldItem.fixedWidth == newItem.fixedWidth
    }
}

/**
 * [GifListAdapter : ListAdapter <Images, ViewHolder> (GifListDiffCallback)]
 * @constructor (private val clickListener: GifItemListener) - a click listener class to listen to [GifItemViewHolder].
 *
 * */
class GifListAdapter(private val clickListener: GifItemListener) :
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
/**
 * [GifItemListener] - a click listener class to listen to [GifItemViewHolder].
 * @constructor takes a lambda with [(String) -> Unit].
 * @sample - contains a function [onClick] that takes a parameter (Images) and performs the function passed to the constructor.
 *
 * */
class GifItemListener(private val clickListener: (url: String) -> Unit) {
    fun onClick(gifImage: Images) {
        clickListener(gifImage.fixedHeight.url)
    }
}