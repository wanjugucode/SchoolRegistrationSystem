package repository

import model.EnrollmentRequest
import model.EnrollmentResponse
import com.example.myapplicatio.api.ApiClient
import com.example.myapplicatio.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class EnrollmentRepository {
    val retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun enrollCourse(access_token:String,enrollmentRequest: EnrollmentRequest):Response<EnrollmentResponse> =
    withContext(Dispatchers.IO){
        return@withContext retrofit.enrollCourse(access_token, enrollmentRequest)

          }

}