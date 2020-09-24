package com.jay.inmobochallenge.repository

import com.jay.inmobochallenge.data.Posts
import com.jay.inmobochallenge.data.Users


interface Repository {

    suspend fun fetchPost(): List<Posts>

    suspend fun fetchUser(): List<Users>

    suspend fun updatePost(postList : List<Posts>)

}