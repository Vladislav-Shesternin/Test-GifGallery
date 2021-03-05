package com.veldan.test_gifgallery.gif_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.veldan.test_gifgallery.R
import com.veldan.test_gifgallery.databinding.FragmentGifListBinding
import com.veldan.test_gifgallery.gif_list.adapter.GifListAdapter

class GifListFragment : Fragment() {

    // Binding
    private lateinit var binding: FragmentGifListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding()

        val adapter = GifListAdapter()
        binding.gifList.adapter = adapter
        adapter.gifList = List(100) { R.drawable.test }

        return binding.root
    }

    // {init}: Binding
    private fun initBinding() {
        binding = FragmentGifListBinding.inflate(layoutInflater)
    }
}