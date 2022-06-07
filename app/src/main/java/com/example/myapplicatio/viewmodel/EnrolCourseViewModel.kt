package com.example.myapplicatio.viewmodel
import com.example.myapplicatio.repository.EnrolCourseRepo
import com.example.myapplicatio.models.EnrolmentRequest
import com.example.myapplicatio.models.EnrolmentResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnrolCourseViewModel @Inject constructor(val enrolCourseRepo: EnrolCourseRepo):ViewModel() {
    val enrolLiveData = MutableLiveData<EnrolmentResponse>()
    val enrolFailed  = MutableLiveData<String>()

    fun enrolCourse(access_token:String,enrolmentRequest: EnrolmentRequest){
        viewModelScope.launch {
            var response = enrolCourseRepo.enrolCourse(access_token,enrolmentRequest)
            if (response.isSuccessful){
                enrolLiveData.postValue(response.body())
            }
            else {
                enrolFailed.postValue(response.errorBody()!!.string())
            }
        }
    }
}