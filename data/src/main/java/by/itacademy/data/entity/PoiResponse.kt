package by.itacademy.data.entity

import com.google.gson.annotations.SerializedName

data class PoiResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("coordinate")
    val coordinate: Coordinate?,

    @SerializedName("fleetType") // Если прислали переменную fleet_Type с подчёркиванием и мы хотим использовать другую то по таким именем ищи переменную
    val fleetType: FleetTypeResponse?,

    @SerializedName("heading") // Указывать переменную которая в JSON пришла
    val heading: Double?
)