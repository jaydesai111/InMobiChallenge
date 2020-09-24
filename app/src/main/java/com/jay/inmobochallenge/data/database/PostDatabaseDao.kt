package com.jay.inmobochallenge.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.inmobochallenge.data.Posts

@Dao
interface PostDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insert(posts: Posts)

    @Query("SELECT * FROM Posts")
    fun getPost(): LiveData<List<Posts>>

    @Query("DELETE FROM Posts")
    suspend fun deletePost()


}