package repository

import model.CourseResponse
import com.example.myapplicatio.api.ApiClient
import com.example.myapplicatio.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CourseRepository {
    var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun fetchCourse(accessToken:String):Response<List<CourseResponse>> =
        withContext(Dispatchers.IO){
            return@withContext retrofit.fetchCourse(accessToken)


        }

}