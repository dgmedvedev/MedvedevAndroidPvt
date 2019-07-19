package by.itacademy.pvt.dz9

import by.itacademy.pvt.dz9.entity.CarResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("t/fake-api/car-service.php") // остальную часть допишет retrofit
    fun getCarsByCoordinate(
        @Query("p1Lat") p1Lat: Double, // дописывает с помощью @Query
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Call<CarResponse>
}