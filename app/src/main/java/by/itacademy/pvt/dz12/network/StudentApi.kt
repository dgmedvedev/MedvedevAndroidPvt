package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentApi {

    @GET("data/student") // остальную часть допишет retrofit
    fun getStudents(
        @Query("pageSize") pageSize: Int // дописывает с помощью @Query
    //    @Query("offset") offset: Int
    ): Observable<MutableList<Student>>

    @GET("data/student/{id}")
    fun getStudentById(
        @Path("id") id: String
    ): Observable<Student>

    @GET("data/student")
    fun searchByName(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") state: String
    ): Observable<MutableList<Student>>
}