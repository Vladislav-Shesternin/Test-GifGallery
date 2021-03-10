package com.veldan.test_gifgallery.fragments.gif_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.veldan.test_gifgallery.databinding.FragmentGifDetailBinding

class GifDetailFragment : Fragment() {

    // Binding
    private lateinit var binding: FragmentGifDetailBinding

    // Components
    private val args by navArgs<GifDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding()
        return binding.root
    }

    // {init}: Binding
    private fun initBinding() {
        binding = FragmentGifDetailBinding.inflate(layoutInflater).apply {
            gifUrl = args.gifUrl
        }
    }
}