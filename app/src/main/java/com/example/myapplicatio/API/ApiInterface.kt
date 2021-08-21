package com.example.myapplicatio.API

import com.example.myapplicatio.Student
import com.example.myapplicatio.models.RegistrationRequest
import com.example.myapplicatio.models.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/student/regiter")
    fun registerStudent(@Body registerStudent: RegistrationRequest):Class<RegistrationResponse>


}