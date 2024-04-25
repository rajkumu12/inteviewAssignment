package com.techmaster.interviewassignment.vm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.techmaster.interviewassignment.data.PostModels
import com.techmaster.interviewassignment.repositories.PostRepository

class PostPagingSource(private val repository: PostRepository) : PagingSource<Int, PostModels>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostModels> {
        return try {
            val nextPageNumber = params.key ?: 1
            val items = repository.getAllPosts(nextPageNumber, 20)
            LoadResult.Page(
                data = items,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (items.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostModels>): Int? {
        return 1
    }
}