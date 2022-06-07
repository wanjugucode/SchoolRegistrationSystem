package com.example.myapplicatio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.myapplicatio.models.LoginRequest
import com.example.myapplicatio.models.LoginResponse
import com.example.myapplicatio.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//class LoginViewModel:ViewModel() {
//    var loginRepository=LoginRepository()
//    var loginLiveData=MutableLiveData<LoginResponse>()
//    var errorLiveData=MutableLiveData<String>()
//
//    fun loginStudents(loginRequest: LoginRequest){
//        viewModelScope.launch{
//        val response = loginRepository.loginStudent(loginRequest)
//        if (response.isSuccessful){
//            loginLiveData.postValue(response.body())
//        }
//        else{
//            errorLiveData.postValue(response.errorBody().toString())
//        }
//    }
//    }
//}



@HiltViewModel
class LoginViewModel @Inject constructor(val userRepository: UserRepository): ViewModel() {
    val loginLiveData = MutableLiveData<LoginResponse>()
    val loginFailedLiveData = MutableLiveData<String>()

    fun loginStudent(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginStudent(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                loginFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

//   val response = userRepository.loginStudent(loginRequest)
