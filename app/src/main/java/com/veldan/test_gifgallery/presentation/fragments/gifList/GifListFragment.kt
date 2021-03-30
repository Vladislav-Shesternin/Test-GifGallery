package com.veldan.test_gifgallery.presentation.fragments.gifList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.veldan.test_gifgallery.databinding.FragmentGifListBinding
import com.veldan.test_gifgallery.framework.databse.GifModel
import com.veldan.test_gifgallery.presentation.fragments.gifList.adapter.GifItemListener
import com.veldan.test_gifgallery.presentation.fragments.gifList.adapter.GifListAdapter
import com.veldan.test_gifgallery.presentation.fragments.gifList.view_model.GifViewModel
import com.veldan.test_gifgallery.framework.network.Images
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GifListFragment : Fragment() {
    private val TAG = this::class.simpleName

    // Binding
    private lateinit var binding: FragmentGifListBinding

    // ViewModel
    @Inject
    lateinit var gifViewModel: GifViewModel

    // Components
    private lateinit var adapter: GifListAdapter

    // Components UI
    private lateinit var gifList: RecyclerView
    private lateinit var lottieNoInternet: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // The sequence of method calls is important !!!
        initBinding()
        return binding.root
    }

    // {init}: Binding
    private fun initBinding() {
        binding = FragmentGifListBinding.inflate(layoutInflater)
        initComponentsUI()
    }

    // {init}: Components UI
    private fun initComponentsUI() {
        binding.also {
            gifList = it.gifList
            lottieNoInternet = it.lottieNoInternet
        }
        initGifListAdapter()
    }

    // {init}: GifListAdapter
    private fun initGifListAdapter() {
        adapter = GifListAdapter(GifItemListener {
            gifViewModel.onGifItemClicked(it)
        })
        gifList.adapter = adapter
        updateGifList()
        observeGifItemClick()
    }

    // {fun}: Update gif list
    private fun updateGifList() {
        gifViewModel.response.observe(viewLifecycleOwner, Observer { gifs ->
            if (gifs != null) {
                lottieNoInternet.apply {
                    cancelAnimation()
                    visibility = View.INVISIBLE
                }
                // Filling RecyclerView
                adapter.submitList(gifs)
            } else {
                // No network connection
                lottieNoInternet.playAnimation()
            }
        })
    }

    // {fun}: Observe GifItem onClick
    private fun observeGifItemClick() {
        gifViewModel.navigateToGifDetail.observe(viewLifecycleOwner, Observer {
            it?.let {
                from_GifListFragment_to_GifDetailFragment(it)
                gifViewModel.onGifDetailNavigated()
            }
        })
    }

    // {nav}: [GifListFragment] ---> [GifDetailFragment]
    private fun from_GifListFragment_to_GifDetailFragment(gifUrl: String) {
        val action = GifListFragmentDirections.actionGifListFragmentToGifDetailFragment(gifUrl)
        findNavController().navigate(action)
    }
}