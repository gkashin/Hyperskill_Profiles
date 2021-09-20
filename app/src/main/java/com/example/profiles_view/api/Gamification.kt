package com.example.profiles_view.api

data class Gamification(
    val active_days: Int,
    val daily_step_completed_count: Int,
    val passed_problems: Int,
    val passed_projects: Int,
    val passed_topics: Int,
    val progress_updated_at: String
)