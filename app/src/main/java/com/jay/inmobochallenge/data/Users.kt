package com.jay.inmobochallenge.data

data class Users(
    val id : String,
    val name : String,
    val username : String,
    val email : String,
    val address: Address,
    val phone : String,
    val website : String,
    val company: Company

)