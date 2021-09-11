package repository

import model.LoginRequest
import model.LoginResponse
import com.example.myapplicatio.api.ApiClient
import com.example.myapplicatio.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    val retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginStudent(loginRequest: LoginRequest):Response<LoginResponse> =
            withContext(Dispatchers.IO) {
                return@withContext retrofit.loginStudent(loginRequest)
        }
 
}