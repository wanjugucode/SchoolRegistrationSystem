package viewModels
import repository.EnrollmentRepository
import model.EnrollmentRequest
import model.EnrollmentResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EnrollCourseViewModel :ViewModel(){
    val enrollmentCourseRepository = EnrollmentRepository()
    val enrollmentlLiveData = MutableLiveData<EnrollmentResponse>()
    val enrollErrorLiveData  = MutableLiveData<String>()
    fun enrollCourse(access_token:String,enrolmentRequest: EnrollmentRequest){
        viewModelScope.launch {
            val response = enrollmentCourseRepository.enrollCourse(access_token,enrolmentRequest)
            if (response.isSuccessful){
                enrollmentlLiveData  .postValue(response.body())
            }
            else {
                enrollErrorLiveData.postValue(response.errorBody()!!.string())
            }
        }
    }

}