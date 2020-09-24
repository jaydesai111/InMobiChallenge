package com.jay.inmobochallenge.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class Posts(
    var userId : String,
    @PrimaryKey
    var id : Int,
    var title : String,
    var body : String
)