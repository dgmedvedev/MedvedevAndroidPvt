package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Completable
import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StudentApi) : StudentRepository {

    override fun getList(pageSize: Int, offset: Int): Observable<MutableList<Student>> {
        return api.getStudents(pageSize, offset)
    }

    override fun getById(id: String): Observable<Student> {
        return api.getStudentById(id)
    }

    override fun filter(pageSize: Int, offset: Int, name: String): Observable<MutableList<Student>> {
        return api.searchByName(pageSize, offset, name)
    }

    override fun addStudent(student: Student): Observable<Student> {
        return api.addStudent(student)
    }

    override fun updateStudent(student: Student): Completable {
        return api.updateStudent(student.id, student)
    }

    override fun deleteStudent(student: Student): Completable {
        return api.deleteStudent(student.id)
    }

    override fun deleteStudentById(id: String): Completable {
        return api.deleteStudent(id)
    }
}