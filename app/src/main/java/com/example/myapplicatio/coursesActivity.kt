package com.example.myapplicatio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class coursesActivity : AppCompatActivity() {
    lateinit var btnNext1:Button
    lateinit var btnPrevious2:Button
    lateinit var rvCourses:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

displayCourse()
    }
    fun displayCourse(){
        var courseList= listOf<Course>(
            Course("Android","Native android development","101","John"),
            Course("Python","backend development","101","John"),
            Course("JS","js for web","101","John"),
            Course("C++","Native android development","101","John"),
            Course("html/css","Native android development","101","John"),
        )
        rvCourses=findViewById<RecyclerView>(R.id.rvCourses)
        var courseAdapter=CoursesRvdapter(courseList)
        rvCourses.layoutManager=LinearLayoutManager(baseContext)
        rvCourses.adapter=courseAdapter
    }
        }

