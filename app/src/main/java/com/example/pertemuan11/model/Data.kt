package com.example.pertemuan11.model

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("employee_name")
    val employeeName: String,

    @SerializedName("employee_age")
    val employeeAge: Int,

    @SerializedName("text")
    val text: String,
)
