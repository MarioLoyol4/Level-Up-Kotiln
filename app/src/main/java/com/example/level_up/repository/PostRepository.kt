package com.example.level_up.repository

import com.example.level_up.model.Post
import com.example.level_up.remote.RetrofitInstance

class PostRepository {
    suspend fun getPosts() : List<Post>{
        return RetrofitInstance.api.getPosts()
    }
}