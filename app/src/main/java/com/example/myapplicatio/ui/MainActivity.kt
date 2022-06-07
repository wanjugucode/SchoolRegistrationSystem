//package com.example.myapplicatio
//
//import android.content.SharedPreferences
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.activity.viewModels
//import com.example.myapplicatio.databinding.ActivityMainBinding
//import com.example.myapplicatio.viewmodel.LoginViewModel
//
//class MainActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding
//    val enrollCourseViewModel: LoginViewModel by viewModels()
//    var error = false
//    lateinit var prefs: SharedPreferences
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}

package com.example.myapplicatio.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.myapplicatio.Constants
import com.example.myapplicatio.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.myapplicatio.models.RegistrationRequest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val registerViewModel: UserViewModel by viewModels()
    var error = false
    lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(Constants.SHAREDPREFS, Context.MODE_PRIVATE)
        redirectStudent()

        val nationality = arrayOf("Kenyan", "Ugandan", "Rwandan", "South Sudan")
        val nationalityAdapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationality)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityAdapter


        binding.btnLoginNext.setOnClickListener {
            val intent = Intent(baseContext,LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun redirectStudent() {
        var accessToken = prefs.getString(Constants.SHAREDPREFS, Constants.EMPTYSTRING)
        if (accessToken!!.isNotEmpty()) {
            startActivity(Intent(baseContext, CoursesActivity::class.java))
        } else {
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnRegister.setOnClickListener {
            validate()
        }

        registerViewModel.registrationLiveData.observe(this, { regResponse ->
            if (!regResponse.studentId.isNullOrEmpty()) {
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
                val intent = Intent(baseContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })
        registerViewModel.registrationFailedLiveData.observe(this, { str ->
            Toast.makeText(baseContext, str, Toast.LENGTH_LONG).show()
        })

    }

    fun validate() {
        if (binding.etEmail.text.toString().isEmpty() || binding.etDob.text.toString().isEmpty()) {
            error = true
            binding.etEmail.error = "Email required"
            binding.etDob.error = "Date of Birth required"
        } else {

            val registrationRequest = RegistrationRequest(
                name = binding.etName.text.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString(),
                nationality = binding.spNationality.selectedItem.toString().uppercase(),
                dateOfBirth = binding.etDob.text.toString(),
                password = binding.etPassword.text.toString(),
                email = binding.etEmail.text.toString()
            )
            UserViewModel.registerUser(registrationRequest)

        }

    }
}












//spinner code for nationality
//Validating views
// Instantiating the registerUser function from the viewmodel in the activity
