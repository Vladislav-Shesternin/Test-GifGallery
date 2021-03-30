package com.veldan.test_gifgallery.presentation.fragments.gifDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veldan.test_gifgallery.databinding.ItemGifDetailListBinding

/**
 *  Using [DiffUtils] for more efficient list work.
 *
 * */
class GifDetailListDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

/**
 * [GifDetailListAdapter : ListAdapter <String, ViewHolder> (GifDetailListDiffCallback)]
 * */
class GifDetailListAdapter:
    ListAdapter<String, GifDetailListAdapter.GifDetailItemViewHolder>(GifDetailListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifDetailItemViewHolder {
        return GifDetailItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GifDetailItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /**
     * [GifDetailItemViewHolder]
     *
     * @constructor (private) - because the value [imageView] is obtained as a result of calling the method [from()]
     * in the [onCreateViewHolder] class, you don't need to pass the constructor parameter anywhere else.
     *
     * */
    class GifDetailItemViewHolder
    private constructor(
        private val binding: ItemGifDetailListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.root

        companion object {
            fun from(parent: ViewGroup): GifDetailItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemGifDetailListBinding.inflate(layoutInflater, parent, false)
                return GifDetailItemViewHolder(binding)
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