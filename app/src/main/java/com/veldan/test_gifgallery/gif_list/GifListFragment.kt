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
import com.veldan.test_gifgallery.gif_list.adapter.GifListAdapter
import com.veldan.test_gifgallery.gif_list.view_model.GifViewModel

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
    private val adapter = GifListAdapter()

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


                    val gifUrlList = mutableListOf<String>()
                    listGifProperty.forEach { gif ->
                        gifUrlList.add(gif.images.fixedWidth.url)
                    }
                    adapter.gifUrlList = gifUrlList
                } else {
                    lottieNoInternet.playAnimation()
                }
            })
    }
}