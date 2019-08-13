package by.itacademy.pvt.dz12

import by.itacademy.pvt.dz12.network.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object Dz12StudentData {
    private const val PAGE_SIZE = 100

    private var listStudents: MutableList<Student> = mutableListOf()

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    fun getListStudent(): MutableList<Student> {
        return listStudents
    }

    fun loadStudentList(callback: Callback) {
        disposable = repository
            .getList(PAGE_SIZE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listStudents = it
                callback.returnResult()
            }
    }

    fun getStudentById(id: String): Student? {
        return listStudents.find { it.id == id }
    }

    fun addStudent(student: Student) {
        var index = listStudents.indexOfFirst { it.id == student.id }

        if (index != -1) {
            listStudents[index] = student

            disposable = repository
                .updateStudent(student)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        } else {
            listStudents.add(student)

            disposable = repository
                .addStudent(student)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    index = listStudents.indexOf(student)
                    listStudents[index] = it
                }
        }
    }

    fun deleteStudent(student: Student) {
        listStudents.remove(student)
        disposable = repository
            .deleteStudent(student)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun filter(search: String): List<Student> {
        return listStudents.filter { it.name.toUpperCase().contains(search.toUpperCase()) }
    }

    fun dispose() {
        disposable?.dispose()
    }
}