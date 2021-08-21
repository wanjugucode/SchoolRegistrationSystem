package com.example.myapplicatio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import com.example.myapplicatio.API.ApiClient
import com.example.myapplicatio.API.ApiClient.retrofit
import com.example.myapplicatio.API.ApiInterface
import com.example.myapplicatio.databinding.ActivityMainBinding
import com.example.myapplicatio.models.RegistrationRequest
import com.example.myapplicatio.models.RegistrationResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        clickRegister()

    }

    fun setupSpinner() {
        var nationalities= arrayOf("select Nationality","Kenyan","Rwandan","South Sudanese","Ugandan")
        var nationalityadapter = ArrayAdapter(baseContext,android.R.layout.simple_spinner_item,nationalities)
        nationalityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityadapter
    }

    fun clickRegister() {
        binding.btnRegister.setOnClickListener {
            var intent= Intent(baseContext, loginActivity::class.java)
            startActivity(intent)
        }
        var error = false
        binding.btnRegister.setOnClickListener {
            var name = binding.etName.text.toString()
            if (name.isEmpty()) {
                error = true
                binding.etName.setError("this field required")
            }
            var dob =binding.etDob.text.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDob.setError("this field required")
            }
            var nationality=""
            if (binding.spNationality.selectedItemPosition!=0){
                nationality = binding.spNationality.selectedItem.toString()
            }
            else{
                error = true
                Toast.makeText(baseContext,"select Nationality", Toast.LENGTH_LONG).show()
            }
            var phoneNumber = binding.etPhoneNumber.text.toString()
            if (phoneNumber.isEmpty()) {
                error = true
                binding.etPhoneNumber.setError("this field required")
            }
            var email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
                binding.etEmail.setError("this field required")
            }
            var password= binding.etPassword.text.toString()
            if (password.isEmpty()) {
                error = true
                binding.etPassword.setError("this field required")
            }

            if (!error){
                binding.pbRegister.visibility=View.VISIBLE
                var lrgRequest= RegistrationRequest(
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    dateOfBirth = dob,
                    nationality = nationality,
                    password = password
                )

                var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
                var request= retrofit.registerStudent(lrgRequest)
                request.enqueue(object : Callback<RegistrationResponse> {
                    override fun onResponse(
                        call: Call<RegistrationResponse>,
                        response: Response<RegistrationResponse>
                    ) {
                        binding.pbRegister.visibility=View.GONE
                        if (response.isSuccessful){
                            Toast.makeText(baseContext,"Registration successfully", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                        binding.pbRegister.visibility=View.GONE
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}


//data class Student(var name: String, var dob: String, var idNo:String, var nationality:String, var phoneNumber:String, var email: String)
