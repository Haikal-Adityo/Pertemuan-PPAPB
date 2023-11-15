package com.example.pertemuan11.retrofit

import com.example.pertemuan11.model.Data
import com.example.pertemuan11.model.PostResponse
import com.example.pertemuan11.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("search")
    fun getcats(): Call<ArrayList<PostResponse>>

}