package by.itacademy.pvt.dz12.mvp

import android.util.Patterns
import by.itacademy.pvt.dz12.Student
import by.itacademy.pvt.dz12.dz12Singleton

class Dz12StudentEditPresenter(private val idStudent: String) {

    private var view: Dz12StudentEditView? = null

    private val pattern = Patterns.WEB_URL

    fun setView(view: Dz12StudentEditView) {
        this.view = view
    }

    fun onDestroy() {
        view = null
    }

    fun getStudentById() {
        val user = dz12Singleton.getStudentById(idStudent)
        if (user == null) {
            view?.onError("User is empty")
            view?.backStack()
        } else
            view?.showStudent(user)
    }

    fun saveStudent(url: String, name: String, age: Int) {
        if (validateData(url, name, age))
            when (dz12Singleton.getStudentById(idStudent)) {
                null -> {
                    dz12Singleton.addStudent(
                        Student(
                            id = System.currentTimeMillis().toString(),
                            imageUrl = url,
                            name = name,
                            age = age
                        )
                    )
                }
                else -> {
                    dz12Singleton.addStudent(
                        Student(
                            id = idStudent,
                            imageUrl = url,
                            name = name,
                            age = age
                        )
                    )
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