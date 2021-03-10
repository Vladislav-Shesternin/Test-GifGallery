package com.veldan.test_gifgallery.fragments.gif_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.veldan.test_gifgallery.databinding.FragmentGifListBinding
import com.veldan.test_gifgallery.databse.GifDatabase
import com.veldan.test_gifgallery.databse.GifModel
import com.veldan.test_gifgallery.fragments.gif_list.adapter.GifItemListener
import com.veldan.test_gifgallery.fragments.gif_list.adapter.GifListAdapter
import com.veldan.test_gifgallery.fragments.gif_list.view_model.GifViewModel
import com.veldan.test_gifgallery.fragments.gif_list.view_model.GifViewModelFactory
import com.veldan.test_gifgallery.network.Images

class GifListFragment : Fragment() {
    private val TAG = this::class.simpleName

    // Binding
    private lateinit var binding: FragmentGifListBinding

    // ViewModel
    private lateinit var gifViewModel: GifViewModel

    // Components UI
    private lateinit var gifList: RecyclerView
    private lateinit var lottieNoInternet: LottieAnimationView

    // Components
    private lateinit var adapter: GifListAdapter


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
        initViewModel()
        initComponentsUI()
    }

    // {init}: ViewModel
    private fun initViewModel() {
        val application = requireNotNull(this.activity).application
        val gifDao = GifDatabase.getDatabase(application).gifDao

        val gifViewModelFactory = GifViewModelFactory(gifDao, application)
        gifViewModel = ViewModelProvider(this, gifViewModelFactory)
            .get(GifViewModel::class.java)
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
        gifViewModel.response.observe(
            viewLifecycleOwner, Observer { listGifProperty ->
                if (listGifProperty != null) {
                    lottieNoInternet.apply {
                        cancelAnimation()
                        visibility = View.INVISIBLE
                    }
                    val gifImages = mutableListOf<Images>()
                    listGifProperty.forEach { gif ->
                        gifImages.add(gif.images)

                        // Insert GifModel in GifDatabase
                        gif.apply {
                            gifViewModel.insertGif(
                                GifModel(
                                    id,
                                    images.fixedWidth.url,
                                    images.fixedHeight.url
                                )
                            )
                        }
                    }
                    // Filling RecyclerView
                    adapter.submitList(gifImages)
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