package com.example.myapplicatio.models

import com.google.gson.annotations.SerializedName

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Courses")
data class Course(
    @PrimaryKey @NonNull @SerializedName("course_id")var courseId:String,
    @SerializedName("course_code")var courseCode:String,
    @SerializedName("course_name")var courseName:String,
    var description:String,
    var instructor:String
)
