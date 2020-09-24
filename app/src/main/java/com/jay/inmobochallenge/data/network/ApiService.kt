package com.jay.inmobochallenge.data.network

import com.jay.inmobochallenge.data.Posts
import com.jay.inmobochallenge.data.Users
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Posts>

    @GET("users")
    suspend fun getUsers(): List<Users>
}