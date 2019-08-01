package by.itacademy.pvt.dz12.network

import by.itacademy.pvt.dz12.Student
import io.reactivex.Observable

class StudentRepositoryRemote(private val api: StudentApi) : StudentRepository {

    override fun getList(pageSize: Int, offset: Int): Observable<List<Student>> {
        return api.getStudents(pageSize, offset)
        /*
        return Observable.create{emitter ->  // здесь мы отправители, там были получателем

            val result = api.getStudents(pageSize, offset).execute()
            val studentList = result.body() ?: emptyList() // получили список

            emitter.onNext(studentList) // получили список
        //    emitter.onNext(studentList) - можно вызывать несколько раз, тоже самое что just
            emitter.onComplete() // больше данных не будет

            // этим методом мы создали грубо говоря val observable из Cw13Activity
        }
        */
    }

    override fun getById(id: String): Observable<Student> {
        return api.getStudentById(id)
    }

    override fun getById111(id: String): Observable<Student> {
        return api.getStudentById111(id)
    }

    override fun filter(search: String): Observable<List<Student>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(id: String): Observable<Boolean> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}