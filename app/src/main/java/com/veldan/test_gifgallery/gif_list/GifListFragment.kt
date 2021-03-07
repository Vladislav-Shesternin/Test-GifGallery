package com.veldan.test_gifgallery.gif_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initBinding()

        viewModel.response.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "onCreateView: $it")
        })

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