package com.example.ar.data

data class Project(
    val id: Long? = null,
    val name: String,
    val description: String,
    val userId: Long,
    val image: String? = null
)