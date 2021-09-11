package model

import com.google.gson.annotations.SerializedName

data class CourseResponse(
   var course_name :String,
   var course_Code:String,
   var description:String,
   var instructor:String,
   var course_id:String
)
