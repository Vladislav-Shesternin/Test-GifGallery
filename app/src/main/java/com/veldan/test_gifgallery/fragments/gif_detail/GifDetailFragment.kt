package com.veldan.test_gifgallery.fragments.gif_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.veldan.test_gifgallery.databinding.FragmentGifDetailBinding
import com.veldan.test_gifgallery.databse.GifDatabase
import com.veldan.test_gifgallery.fragments.gif_detail.adapter.GifDetailListAdapter
import com.veldan.test_gifgallery.fragments.gif_detail.view_model.GifDetailViewModel
import com.veldan.test_gifgallery.fragments.gif_detail.view_model.GifDetailViewModelFactory
import com.veldan.test_gifgallery.fragments.gif_list.view_model.GifViewModel
import com.veldan.test_gifgallery.fragments.gif_list.view_model.GifViewModelFactory

class GifDetailFragment : Fragment() {

    // Binding
    private lateinit var binding: FragmentGifDetailBinding

    // ViewModel
    private lateinit var gifDetailViewModel: GifDetailViewModel

    // Components
    private val args by navArgs<GifDetailFragmentArgs>()
    private val adapter = GifDetailListAdapter()

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
        binding = FragmentGifDetailBinding.inflate(layoutInflater)
        initViewModel()
    }

    // {init}: ViewModel
    private fun initViewModel() {
        val application = requireNotNull(this.activity).application
        val gifDao = GifDatabase.getDatabase(application).gifDao

        val gifDetailViewModelFactory = GifDetailViewModelFactory(gifDao)
        gifDetailViewModel = ViewModelProvider(this, gifDetailViewModelFactory)
            .get(GifDetailViewModel::class.java).apply { getAllGifs() }

        initGifDetailListAdapter()
    }

    // {init}: GifDetailListAdapter
    private fun initGifDetailListAdapter() {
        binding.rvGifs.adapter = adapter
        updateGifDetailList()
    }

    // {fun}: updateGifDetailList
    private fun updateGifDetailList() {
        gifDetailViewModel.gifs.observe(viewLifecycleOwner, Observer {
            val gifs = mutableListOf(args.gifUrl) + it.map { gifs -> gifs.urlFixedHeight }.filterNot { it == args.gifUrl }
            adapter.submitList(gifs)
        })
    }
}