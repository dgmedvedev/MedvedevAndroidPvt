package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StudentApi) : StudentRepository {

    override fun getList(pageSize: Int, offset: Int): Observable<List<Student>> {
        return api.getStudents(pageSize, offset)
    }

    override fun getById(id: String): Observable<Student> {
        return api.getStudentById(id)
    }

    override fun filter(search: String): Observable<List<Student>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(id: String): Observable<Boolean> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}