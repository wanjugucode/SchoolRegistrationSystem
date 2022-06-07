package ui



import com.example.myapplicatio.models.EnrolmentRequest
import com.example.myapplicatio.viewmodel.CourseViewModel
import com.example.myapplicatio.viewmodel.EnrolCourseViewModel

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicatio.Constants
import com.example.myapplicatio.CoursesResponseAdapter
import com.example.myapplicatio.R
import com.example.myapplicatio.databinding.ActivityCoursesBinding


class coursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    lateinit var sharedPres:SharedPreferences
    val coursesViewModel: CourseViewModel by viewModels()
    val enrollCourseViewModel: EnrolCourseViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
       setContentView( binding.root)
        sharedPres=getSharedPreferences(Constants.REGISTRATION_PREFS, MODE_PRIVATE)


    }
    override fun onResume() {
        super.onResume()
    enroll()
      val accessToken = sharedPres.getString(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING)
      var bearer = "Bearer $accessToken"
        val studentIdIntent = intent.getStringExtra("STUDENT_ID")
        val courseIntent = intent.getStringExtra("courseId")

        if (accessToken!!.isNotEmpty()){
            coursesViewModel.fetchCourse(accessToken)
            val enrollRequest = EnrolmentRequest(
                student_id = studentIdIntent.toString(),
                course_id = courseIntent.toString()
            )
            enrollCourseViewModel.enrollCourse(bearer, enrollRequest)
        }
    else{            startActivity(Intent(baseContext, loginActivity::class.java))
    }
    val rvCoursesResponseAdapter = binding.rvCourses
 rvCoursesResponseAdapter.layoutManager = LinearLayoutManager(baseContext)

 coursesViewModel.courseLiveData.observe(this, {courseList->
val coursesResponseAdapter = CoursesResponseAdapter(courseList,baseContext)
rvCoursesResponseAdapter.adapter = coursesResponseAdapter
Toast.makeText(baseContext, "${courseList.size} courses fetched", Toast.LENGTH_LONG).show()
    })
    coursesViewModel.errorLiveData.observe(this, {
    error->Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
    })
    }

    fun enroll() {
        enrollCourseViewModel .enrollmentlLiveData.observe(this, { enrolResponse ->
            Toast.makeText(baseContext, "${enrolResponse.active}", Toast.LENGTH_LONG).show()
        })
        enrollCourseViewModel .enrollErrorLiveData.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }


}





