package com.jay.inmobochallenge.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jay.inmobochallenge.R
import com.jay.inmobochallenge.databinding.FragmentHomeBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    val postAdapter: PostAdapter by lazy {
        PostAdapter()
    }
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        fragmentHomeBinding.lifecycleOwner = this
        fragmentHomeBinding.homeViewModel = viewModel

        fragmentHomeBinding.btStart.setOnClickListener {
            viewModel.getData()
            fragmentHomeBinding.progressBar.visibility = View.VISIBLE
            fragmentHomeBinding.btStart.visibility = View.GONE
        }

        fragmentHomeBinding.btDelete.setOnClickListener {
            viewModel.deleteData()
        }
        fragmentHomeBinding.rvPost.run {
            adapter = postAdapter
        }

        viewModel.postList.observe(viewLifecycleOwner, Observer {
            fragmentHomeBinding.progressBar.visibility = View.GONE

            if (it.size == 0) {
                fragmentHomeBinding.btStart.visibility = View.VISIBLE
                fragmentHomeBinding.btDelete.visibility = View.GONE

            } else {
                fragmentHomeBinding.btStart.visibility = View.GONE
                fragmentHomeBinding.btDelete.visibility = View.VISIBLE


            }

            Log.i("HomeFragment", "this is value of data " + it)
            postAdapter.submitList(it)
        })
        return fragmentHomeBinding.root
    }


}