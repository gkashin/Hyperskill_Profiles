package com.example.profiles_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profiles_view.api.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://hyperskill.org"

class MainActivity : AppCompatActivity(), ProfileAdapter.OnItemClickListener {

    private var TAG = "MainActivity"
    private lateinit var profiles: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUsersData()
    }

    private fun fadeInFromBlack() {
        vBlackScreen.animate().apply {
            alpha(0f)
            duration = 3000
        }.start()
    }

    private fun setupRecyclerView() {
        rvRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rvRecyclerView.adapter = ProfileAdapter(profiles, this)
    }

    private fun getUsersData() {
        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getUserProfiles().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d(TAG, data.users.size.toString())
                    profiles = data.users as MutableList<User>

                    withContext(Dispatchers.Main) {
                        setupRecyclerView()
                        fadeInFromBlack()
                        progressBar.visibility = View.GONE
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Something went wrong with the Internet connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClick(position: Int) {
        val selectedProfile = profiles[position]
        val intent = Intent(this, ProfileDetailActivity::class.java)
        intent.putExtra("fullname", selectedProfile.fullname)
        intent.putExtra("avatar", selectedProfile.avatar)
        intent.putExtra("projects", selectedProfile.gamification.passed_projects)
        intent.putExtra("problems", selectedProfile.gamification.passed_problems)
        intent.putExtra("topics", selectedProfile.gamification.passed_topics)
        intent.putExtra("bio", selectedProfile.bio)

        startActivity(intent)
    }
}