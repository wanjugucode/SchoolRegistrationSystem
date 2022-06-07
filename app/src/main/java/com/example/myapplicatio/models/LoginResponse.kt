package com.example.myapplicatio.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var email:String,
    @SerializedName("access_token") var accesstoken:String,
    @SerializedName("student_id")var studentid:String,
    @SerializedName("student_password")var password:String,
    var message :String


    )
