package com.example.profiles_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ProfileDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        val fullnameView: TextView = findViewById(R.id.tvProfileFullname)
        val avatarView: ImageView = findViewById(R.id.ivAvatar)
        val projectsView: TextView = findViewById(R.id.tvProfileProjects)
        val problemsView: TextView = findViewById(R.id.tvProfileProblems)
        val topicsView: TextView = findViewById(R.id.tvProfileTopics)
        val bioView: TextView = findViewById(R.id.tvProfileBiography)

        val bundle: Bundle ?= intent.extras
        val fullname = bundle!!.getString("fullname")
        val avatar = bundle!!.getString("avatar")
        val projects = bundle!!.getInt("projects")
        val problems = bundle!!.getInt("problems")
        val topics = bundle!!.getInt("topics")
        var bio = bundle!!.getString("bio")

        if (bio == null || bio.isEmpty()) {
            bio = "No bio specified"
        }

        fullnameView.text = fullname
        Glide.with(avatarView)
            .load(avatar)
            .into(avatarView)
        projectsView.text = "Projects completed: ${projects.toString()}"
        problemsView.text = "Problems solved: ${problems.toString()}"
        topicsView.text = "Tracks completed: ${topics.toString()}"
        bioView.text = bio
    }
}