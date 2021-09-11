package com.example.myapplicatio.api


import model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/regiter")
    suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>
    @POST("/student/login")
    suspend fun loginStudent(@Body loginRequest: LoginRequest):Response<LoginResponse>
    @GET("/course")
    suspend fun fetchCourse(@Header("Authorization")token:String):Response<List<CourseResponse>>
    @POST("/enrolments")
    suspend fun enrollCourse(@Header ("Authorization")token: String,@Body enrollmentRequest: EnrollmentRequest):Response<EnrollmentResponse>


}


































//   fun loginStudent(@Body loginResponse:LoginResponse):Call<LoginResponse>