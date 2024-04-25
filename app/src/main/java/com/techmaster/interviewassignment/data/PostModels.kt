package com.techmaster.interviewassignment.data

import java.io.Serializable

class PostModels(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String,
): Serializable