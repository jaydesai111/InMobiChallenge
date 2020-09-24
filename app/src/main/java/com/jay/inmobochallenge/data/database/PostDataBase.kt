package com.jay.inmobochallenge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jay.inmobochallenge.data.Posts


@Database(entities = [Posts::class], version = 1, exportSchema = false)
abstract class PostDataBase : RoomDatabase(){

  abstract fun getPostDatabaseDao(): PostDatabaseDao


}