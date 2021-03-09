package com.veldan.test_gifgallery.gif_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.veldan.test_gifgallery.R
import com.veldan.test_gifgallery.databinding.FragmentGifListBinding
import com.veldan.test_gifgallery.gif_list.adapter.GifItemListener
import com.veldan.test_gifgallery.gif_list.adapter.GifListAdapter
import com.veldan.test_gifgallery.gif_list.view_model.GifViewModel
import com.veldan.test_gifgallery.network.Images

class GifListFragment : Fragment() {
    private val TAG = this::class.simpleName

    // Binding
    private lateinit var binding: FragmentGifListBinding

    // ViewModel
    private val viewModel by viewModels<GifViewModel>()

    // Components UI
    private lateinit var gifList: RecyclerView
    private lateinit var lottieNoInternet: LottieAnimationView

    // Components
    private val adapter = GifListAdapter(GifItemListener {
        Log.i("GifListFragment", "onClick: \n$it")
    })

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
        gifList.adapter = adapter
        updateGifList()
    }

    // {fun}: Update gif list
    private fun updateGifList() {
        viewModel.response.observe(
            viewLifecycleOwner, Observer { listGifProperty ->
                if (listGifProperty != null) {
                    lottieNoInternet.apply {
                        cancelAnimation()
                        visibility = View.INVISIBLE
                    }
                    val gifImages = mutableListOf<Images>()
                    listGifProperty.forEach { gif ->
                        gifImages.add(gif.images)
                    }
                    adapter.submitList(gifImages)
                } else {
                    lottieNoInternet.playAnimation()
                }
            })
    }
}