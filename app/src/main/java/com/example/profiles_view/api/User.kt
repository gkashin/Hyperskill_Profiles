package com.example.profiles_view.api

data class User(
    val avatar: String,
    val badge_title: String,
    val bio: String,
    val comments_posted: CommentsPosted,
    val completed_tracks: List<Any>,
    val country: String,
    val discord_id: Any,
    val experience: String,
    val facebook_username: String,
    val fullname: String,
    val gamification: Gamification,
    val github_username: String,
    val id: Int,
    val invitation_code: String,
    val languages: List<String>,
    val linkedin_username: String,
    val reddit_username: String,
    val selected_tracks: List<Int>,
    val twitter_username: String,
    val username: String
)