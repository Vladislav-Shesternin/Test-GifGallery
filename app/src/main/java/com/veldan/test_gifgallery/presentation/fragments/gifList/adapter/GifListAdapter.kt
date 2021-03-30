package com.veldan.test_gifgallery.presentation.fragments.gifList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veldan.test_gifgallery.databinding.ItemGifListBinding
import com.veldan.test_gifgallery.domain.Gif
import com.veldan.test_gifgallery.presentation.fragments.gifList.adapter.GifListAdapter.GifItemViewHolder
import com.veldan.test_gifgallery.framework.network.Images

/**
 *  Using [DiffUtils] for more efficient list work.
 *
 * */
class GifListDiffCallback : DiffUtil.ItemCallback<Gif>() {

    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id === newItem.id
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem == newItem
    }
}

/**
 * [GifListAdapter : ListAdapter <Images, ViewHolder> (GifListDiffCallback)]
 * @constructor (private val clickListener: GifItemListener) - a click listener class to listen to [GifItemViewHolder].
 *
 * */
class GifListAdapter(private val clickListener: GifItemListener) :
    ListAdapter<Gif, GifListAdapter.GifItemViewHolder>(GifListDiffCallback()) {

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

        fun bind(gif: Gif, clickListener: GifItemListener) {
            binding.also {
                it.gif = gif
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
    fun onClick(gif: Gif) {
        clickListener(gif.urlFixedHeight)
    }
}