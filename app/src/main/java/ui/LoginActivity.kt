package ui

import model.LoginRequest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplicatio.databinding.ActivityLoginBinding
import viewModels.LoginViewModel
import android.content.Context
import android.content.SharedPreferences
import androidx.activity.viewModels


class loginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences("REGISTRATION_PREFS", Context.MODE_PRIVATE)
    }
    override fun onResume() {
        super.onResume()
        binding.btnLoginNext.setOnClickListener {
       validate()
        }
        loginViewModel.loginLiveData.observe(this, { loginResponse ->
            if (!loginResponse.studentid.isEmpty()) {
                val intent = Intent(baseContext, coursesActivity::class.java)
                startActivity(intent)
            }
        })

        loginViewModel.errorLiveData.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
        binding.btnLoginPrevious.setOnClickListener {
            val intent=Intent(baseContext,LoginViewModel ::class.java)
            startActivity(intent)
        }
    }
    fun validate(){
            if (binding.tvEmail.text.toString().isEmpty() || binding.tvPassword.text.toString()
                    .isEmpty()
            ) {
                binding.tvEmail.setError("Email Required")
                binding.tvPassword.setError("Password Required")
            }
            else {
                val loginRequest = LoginRequest(
                    email = binding.tvEmail.text.toString(),
                    password = binding.tvPassword.text.toString(),
                )
                loginViewModel.loginStudents(loginRequest)
            }

    }

}

















































































//            val retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
////            val request= retrofit.loginStudent(lgnRequest)
//            val request=retrofit.loginStudent(lgnRequest)
//            request.enqueue(object : Callback<LoginResponse?> {
//                override fun onResponse(
//                    call: Call<LoginResponse?>,
//                    response: Response<LoginResponse?>
//                ) {
//                    binding.pbLogin.visibility= View.VISIBLE
//                    if (response.isSuccessful){
//                        Toast.makeText(baseContext, "Login is successful", Toast.LENGTH_LONG).show()
//                    }
//                    else{
//                        Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
//                    }
//
//
//                }
//
//                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
//                    binding.pbLogin.visibility=View.GONE
//                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//                }
//            })