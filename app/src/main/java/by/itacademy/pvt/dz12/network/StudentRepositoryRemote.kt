package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StudentApi) : StudentRepository {

    override fun getList(pageSize: Int): Observable<MutableList<Student>> {
        return api.getStudents(pageSize)
    }

    override fun getById(id: String): Observable<Student> {
        return api.getStudentById(id)
    }

    override fun filter(pageSize: Int, offset: Int, name: String): Observable<MutableList<Student>> {
        return api.searchByName(pageSize, offset, name)
    }

    override fun remove(id: String): Observable<Boolean> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}