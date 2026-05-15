package com.example.ar.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST

interface ProjectApi {

    @GET("projects/user/{userId}")
    suspend fun getProjectsByUser(
        @Path("userId") userId: Long
    ): List<Project>



    @POST("projects")
    suspend fun createProject(@Body project: Project): Project
}