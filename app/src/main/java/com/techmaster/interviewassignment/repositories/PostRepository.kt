package com.techmaster.interviewassignment.repositories

import com.techmaster.interviewassignment.data.PostModels
import com.techmaster.interviewassignment.interfaces.ApiService

class PostRepository(private val apiService: ApiService) {
    suspend fun getAllPosts(page: Int, limit: Int): List<PostModels> {
        return apiService.getAllPosts(page, limit)
    }


}