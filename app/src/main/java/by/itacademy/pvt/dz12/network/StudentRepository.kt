package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Observable

interface StudentRepository {

    fun getList(pageSize: Int): Observable<MutableList<Student>>

    fun getById(id: String): Observable<Student>

    fun filter(pageSize: Int, offset: Int, name: String): Observable<MutableList<Student>>

    fun remove(id: String): Observable<Boolean>
}