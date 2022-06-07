package com.example.myapplicatio

import ui.coursesActivity
import com.example.myapplicatio.models.CoursesResponse
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoursesResponseAdapter(var courseList: List<CoursesResponse>, var context: Context):RecyclerView.Adapter<CoursesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
   val itemView=LayoutInflater.from(parent.context).inflate(R.layout.course_item_view,parent,false)
        return CoursesViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val currentCourse=courseList.get(position)
        holder.tvTextView2.text=currentCourse.course_name
        holder.tvDescription.text=currentCourse.description
        holder.tvCourseCode.text=currentCourse.course_Code
        holder.tvInstructor.text=currentCourse.instructor
        holder.ivCourses.setOnClickListener {
        var intent = Intent(context, coursesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("courseId",currentCourse.course_id)
        context.startActivity(intent)
    }
    }
    override fun getItemCount(): Int {
       return courseList.size
    }
}
class CoursesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var tvTextView2 = itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    var tvCourseCode = itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvInstructor = itemView.findViewById<TextView>(R.id.tvInstructor)
    var ivCourses=itemView.findViewById<ImageView>(R.id.ivCourses)
}

