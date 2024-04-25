package com.techmaster.interviewassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.techmaster.interviewassignment.R
import com.techmaster.interviewassignment.databinding.ActivityMainBinding
import com.techmaster.interviewassignment.interfaces.ApiService
import com.techmaster.interviewassignment.repositories.PostRepository
import com.techmaster.interviewassignment.vm.ItemViewModelFactory
import com.techmaster.interviewassignment.vm.PostViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels {
        ItemViewModelFactory(PostRepository(ApiService.create()))
    }
    private val adapter=PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        initRecyclerView()
        observeViewModel()
        setContentView(binding.root)
    }
    private fun initRecyclerView() {
        binding.recycler.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recycler.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.items.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}