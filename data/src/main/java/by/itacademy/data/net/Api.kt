package by.itacademy.data.net

import by.itacademy.data.entity.CarResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("kiparo.ru/t/fake-api/car-service.php")
    fun getCarsByCoordinate(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): Single<CarResponse>
}