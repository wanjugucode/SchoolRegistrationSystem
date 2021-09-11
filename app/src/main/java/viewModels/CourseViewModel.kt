package viewModels

import repository.CourseRepository
import model.CourseResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CourseViewModel: ViewModel() {
    var courseRepository=CourseRepository()
    var courseLiveData= MutableLiveData<List<CourseResponse>>()
    var errorLiveData=MutableLiveData<String>()

    fun fetchCourse(accessToken:String){
        viewModelScope.launch{
            var response=courseRepository.fetchCourse(accessToken)
            if (response.isSuccessful){
                courseLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody().toString())
            }
            }

        }

}


