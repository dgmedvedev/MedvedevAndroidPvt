package by.itacademy.pvt.dz12.mvp

import android.util.Patterns
import by.itacademy.pvt.R
import by.itacademy.pvt.app.App
import by.itacademy.pvt.dz12.Student
import by.itacademy.pvt.dz12.network.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12StudentEditPresenter(private val idStudent: String?) {

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    private var view: Dz12StudentEditView? = null

    private val idNotFound = App.instance.getString(R.string.id_not_found)
    private val defaultValue = App.instance.getString(R.string.default_value)
    private val studentNotFound = App.instance.getString(R.string.student_not_found)

    private val pattern = Patterns.WEB_URL

    fun setView(view: Dz12StudentEditView) {
        this.view = view
    }

    fun detach() {
        disposable?.dispose()
        view = null
    }

    fun showStudentById() {
        idStudent?.let { id ->
            disposable = repository
                .getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { student ->
                    if (student == null) {
                        view?.onError(studentNotFound)
                        view?.backStack()
                    } else
                        view?.showStudent(student)
                }
        } ?: view?.onError(idNotFound)
    }

    fun saveStudent(url: String, name: String, age: Int) {
        idStudent?.let { id ->
            if (validateData(url, name, age)) {
                when {
                    id != defaultValue -> {
                        disposable = repository
                            .updateStudent(Student(id, url, name, age))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    }
                    id == defaultValue -> {
                        val newId = System.currentTimeMillis().toString()
                        disposable = repository
                            .addStudent(Student(newId, url, name, age))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                    }
                    else -> view?.onError(idNotFound)
                }
            }
        }
    }

    private fun validateData(url: String, name: String, age: Int): Boolean {
        if (!pattern.matcher(url).matches()) throw HttpFormatException()
        else if (name.isEmpty()) {
            view?.onError("Name must be filled in")
            return false
        } else if (age < 0) {
            view?.onError("Age must be a positive integer")
            return false
        }
        return true
    }

    internal inner class HttpFormatException : Exception()
}