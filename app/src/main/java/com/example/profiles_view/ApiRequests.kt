package com.example.profiles_view

import com.example.profiles_view.api.ProfileJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET("/api/users")
    fun getUserProfiles(): Call<ProfileJson>
}