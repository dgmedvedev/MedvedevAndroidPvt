package by.itacademy.pvt.dz11.mvp

import android.util.Patterns
import by.itacademy.pvt.dz6.Singleton
import by.itacademy.pvt.dz6.Student

class Dz11StudentEditPresenter(private val idStudent: Long) {

    private var view: Dz11StudentEditView? = null

    private val pattern = Patterns.WEB_URL

    fun setView(view: Dz11StudentEditView) {
        this.view = view
    }

    fun getStudentById() {
        val user = Singleton.getStudentById(idStudent)
        if (user == null) {
            view?.onError("User is empty")
            view?.backStack()
        } else
            view?.showStudent(user)
    }

    fun saveStudent(url: String, name: String, age: Int) {
        if (validateData(url, name, age))
            when (Singleton.getStudentById(idStudent)) {
                null -> {
                    Singleton.addStudent(
                        Student(
                            id = System.currentTimeMillis(),
                            imageUrl = url,
                            name = name,
                            age = age
                        )
                    )
                }
                else -> {
                    Singleton.addStudent(
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
            view?.onError("Name: Must be filled in")
            return false
        } else if (age < 0) {
            view?.onError("Age: Must be a positive integer")
            return false
        }
        return true
    }

    internal inner class HttpFormatException : Exception()
}