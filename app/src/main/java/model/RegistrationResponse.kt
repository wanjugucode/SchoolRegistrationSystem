package model

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("phone_number") var phoneNumber:String,
    var nationality:String,
    @SerializedName("date_of_birth")var dateOfBirth:String,
    @SerializedName("student_id")var studentId:String
)
