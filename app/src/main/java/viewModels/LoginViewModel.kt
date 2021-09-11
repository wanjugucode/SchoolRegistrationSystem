package viewModels

import repository.LoginRepository
import model.LoginRequest
import model.LoginResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    var loginRepository=LoginRepository()
    var loginLiveData=MutableLiveData<LoginResponse>()
    var errorLiveData=MutableLiveData<String>()

    fun loginStudents(loginRequest: LoginRequest){
        viewModelScope.launch{
        val response = loginRepository.loginStudent(loginRequest)
        if (response.isSuccessful){
            loginLiveData.postValue(response.body())
        }
        else{
            errorLiveData.postValue(response.errorBody().toString())
        }
    }
    }
}