package com.jay.inmobochallenge.repository

import android.util.Log
import com.jay.inmobochallenge.data.Posts
import com.jay.inmobochallenge.data.Users
import com.jay.inmobochallenge.data.database.PostDatabaseDao
import com.jay.inmobochallenge.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject


class RepositoryImp @Inject constructor(val retrofit: Retrofit, val postDatabaseDao: PostDatabaseDao): Repository {
    override suspend fun fetchPost(): List<Posts> {
        return retrofit.create(ApiService::class.java).getPosts()
    }

    override suspend fun fetchUser(): List<Users> {
        return retrofit.create(ApiService::class.java).getUsers()
    }

    override suspend fun updatePost(postList : List<Posts>)
    {
        withContext(Dispatchers.IO) {
                try{

                    // Launch each insert as a separate job to be executed in parallel
                    launch {
                        for(post in postList) {
                            postDatabaseDao.insert(post)
                        }
                    }
                }catch (e: Exception){
                    Log.i("ERROR", "this is error ${e.message}")
                    // Could send an error report here or something but I don't think you should throw an error to the UI
                    // Since there could be many blog posts being inserted/updated.
                }

        }
    }

}