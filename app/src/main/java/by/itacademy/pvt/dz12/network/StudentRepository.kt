package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Observable
import io.reactivex.Completable

interface StudentRepository {
    fun getList(pageSize: Int, offset: Int): Observable<MutableList<Student>>

    fun getById(id: String): Observable<Student>

    fun filter(pageSize: Int, offset: Int, name: String): Observable<MutableList<Student>>

    fun addStudent(student: Student): Observable<Student>

    fun updateStudent(student: Student): Completable

    fun deleteStudent(student: Student): Completable

    fun deleteStudentById(id: String): Completable
}