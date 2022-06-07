package com.example.myapplicatio.repository

import com.example.myapplicatio.models.EnrolmentRequest
import com.example.myapplicatio.models.EnrolmentResponse
import com.example.myapplicatio.api.ApiClient
import com.example.myapplicatio.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class EnrolCourseRepo {
    val retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun enrollCourse(access_token:String,enrollmentRequest: EnrolmentRequest):Response<EnrolmentResponse> =
    withContext(Dispatchers.IO){
        return@withContext retrofit.enrollCourse(access_token, enrollmentRequest)

          }

}