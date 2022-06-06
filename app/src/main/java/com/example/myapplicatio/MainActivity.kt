package com.example.myapplicatio

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplicatio.databinding.ActivityMainBinding
import viewModels.LoginViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val enrollCourseViewModel: LoginViewModel by viewModels()
    var error = false
    lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}