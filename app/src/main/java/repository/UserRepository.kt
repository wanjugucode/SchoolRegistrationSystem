package repository

import model.RegistrationRequest
import model.RegistrationResponse
import com.example.myapplicatio.api.ApiClient
import com.example.myapplicatio.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerStudent(registrationRequest: RegistrationRequest): Response<RegistrationResponse> =
        withContext(Dispatchers.IO) {
            val response = retrofit.registerStudent(registrationRequest)
            return@withContext response
        }

}