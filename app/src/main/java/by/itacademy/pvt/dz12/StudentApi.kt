package by.itacademy.pvt.dz12

import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Query

interface StudentApi {

    @GET("data/student") // остальную часть допишет retrofit
    fun getStudents(
        @Query("pageSize") pageSize: Int, // дописывает с помощью @Query
        @Query("offset") offset: Int
    ): Observable<List<Student>>

    @GET("data/student/9D650B02-905D-679B-FF05-DAD9C1B32000")
    fun getStudentById(
        @Query("objectId") objectId: String
    ): Observable<Student>
}