package viewModels

import repository.UserRepository
import model.RegistrationRequest
import model.RegistrationResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository=UserRepository()
    val registrationLiveData=MutableLiveData<RegistrationResponse>()
    var errorLiveData=MutableLiveData<String>()

    fun registerStudents(registrationRequest: RegistrationRequest){
        viewModelScope.launch{
            val response = userRepository.registerStudent(registrationRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody().toString())
            }

        }
    }
}