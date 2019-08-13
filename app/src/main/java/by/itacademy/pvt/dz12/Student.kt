package by.itacademy.pvt.dz12

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("objectId")
    val id: String,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("age")
    val age: Int
)