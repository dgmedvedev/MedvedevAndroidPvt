package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.DELETE

interface StudentApi {

    @GET("data/student") // остальную часть допишет retrofit
    fun getStudents(
        @Query("pageSize") pageSize: Int, // дописывает с помощью @Query
        @Query("offset") offset: Int
    ): Observable<MutableList<Student>>

    @GET("data/student/{objectId}")
    fun getStudentById(
        @Path("objectId") id: String
    ): Observable<Student>

    @GET("data/student")
    fun searchByName(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") state: String
    ): Observable<MutableList<Student>>

    @POST("data/student")
    fun addStudent(
        @Body student: Student
    ): Observable<Student>

    @PUT("data/student/{objectId}")
    fun updateStudent(
        @Path("objectId") id: String,
        @Body student: Student
    ): Completable

    @DELETE("data/student/{objectId}")
    fun deleteStudent(
        @Path("objectId") id: String
    ): Completable
}