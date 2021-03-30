package com.veldan.test_gifgallery.presentation.fragments.gifDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.veldan.test_gifgallery.databinding.FragmentGifDetailBinding
import com.veldan.test_gifgallery.interactors.OpenUrlAsFirst
import com.veldan.test_gifgallery.presentation.fragments.gifDetail.adapter.GifDetailListAdapter
import com.veldan.test_gifgallery.presentation.fragments.gifDetail.view_model.GifDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GifDetailFragment : Fragment() {

    // Binding
    private lateinit var binding: FragmentGifDetailBinding

    // Args
    private val args by navArgs<GifDetailFragmentArgs>()

    // ViewModel
    @Inject
    lateinit var gifDetailViewModelFactory: GifDetailViewModel.AssistedFactory
    val viewModel: GifDetailViewModel by viewModels{
        GifDetailViewModel.provideFactory(gifDetailViewModelFactory, args.gifUrl)
    }

    // Adapters
    private val adapter = GifDetailListAdapter()

    // Components UI
    private lateinit var rvGifs: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding()
        return binding.root
    }

    // ------------------------------------------------------------| Binding |
    // {init}: Binding
    private fun initBinding() {
        binding = FragmentGifDetailBinding.inflate(layoutInflater)
        initComponentsUI()
    }

    // ------------------------------------------------------------| Components UI |
    // {init}: Components UI
    private fun initComponentsUI() {
        binding.also {
            rvGifs = it.rvGifs
        }
        initAdapters()
    }

    // ------------------------------------------------------------| Adapters |
    // {init}: Adapters
    private fun initAdapters(){
        initGifDetailListAdapter() // <<< GifDetailListAdapter
    }

    // ------------------------------------------------------------| sub Adapters |
    // {init fun}: GifDetailListAdapter
    private fun initGifDetailListAdapter() {
        rvGifs.adapter = adapter
        updateGifDetailList()
    }

    // ------------------------------------------------------------| Functions |
    // {fun}: updateGifDetailList
    private fun updateGifDetailList() {
        viewModel.gifs.observe(viewLifecycleOwner, Observer { urlsFixedHeight ->
            adapter.submitList(urlsFixedHeight)
        })
    }
}