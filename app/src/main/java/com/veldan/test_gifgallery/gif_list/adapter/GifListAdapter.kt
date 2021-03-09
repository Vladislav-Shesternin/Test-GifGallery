package com.veldan.test_gifgallery.gif_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veldan.test_gifgallery.R

class GifListAdapter : RecyclerView.Adapter<GifListAdapter.GifItemViewHolder>() {

    var gifUrlList = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val imageView = layoutInflater.inflate(R.layout.item_gif_list, parent, false) as ImageView
        return GifItemViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: GifItemViewHolder, position: Int) {
        val item = gifUrlList[position]
        holder.bind(item)
    }

    override fun getItemCount() = gifUrlList.size

    class GifItemViewHolder(private val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(gif_url: String) {
            Glide.with(imageView.rootView)
                .load(gif_url)
                .thumbnail(
                    Glide.with(imageView.rootView)
                        .load(R.drawable.loading))
                .into(imageView)
        }
    }
}