package com.techmaster.interviewassignment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.techmaster.interviewassignment.data.PostModels
import com.techmaster.interviewassignment.repositories.PostRepository
import kotlinx.coroutines.flow.Flow

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    val items: Flow<PagingData<PostModels>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        PostPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 20 // Adjust as per your API
    }
}