package by.itacademy.data.entity

import com.google.gson.annotations.SerializedName

data class CarResponse(

    @SerializedName("poiList")
    val poiList: List<PoiResponse>
)