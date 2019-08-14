package by.itacademy.pvt.dz19.entity

import com.google.gson.annotations.SerializedName

data class CoordinateDb(
    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double
)