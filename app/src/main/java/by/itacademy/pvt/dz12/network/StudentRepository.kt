package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Observable

interface StudentRepository {

    fun getList(pageSize: Int, offset: Int): Observable<List<Student>>

    fun getById(id: String): Observable<Student>

    fun getById111(id: String): Observable<Student>

    fun filter(search: String): Observable<List<Student>>

    fun remove(id: String): Observable<Boolean>
}