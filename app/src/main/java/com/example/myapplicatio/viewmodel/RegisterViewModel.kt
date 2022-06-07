package com.example.myapplicatio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicatio.models.RegistrationRequest
import com.example.myapplicatio.models.RegistrationResponse
import com.example.myapplicatio.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(val userRepository: UserRepository):ViewModel() {
    val registrationLiveData = MutableLiveData<RegistrationResponse>()
    val registrationFailedLiveData = MutableLiveData<String>()

    fun registerUser(registrationRequest: RegistrationRequest){
//    launch - creating an actual coroutine
        viewModelScope.launch {
            userRepository.registerStudent(registrationRequest)
        }
    }
}

//            val response = userRepository.registerStudent(registrationRequest)
//            if (response.isSuccessful){
//                registrationLiveData.postValue(response.body())
//            }
//            else{
//                registrationFailedLiveData.postValue(response.errorBody()?.string())
//            }
