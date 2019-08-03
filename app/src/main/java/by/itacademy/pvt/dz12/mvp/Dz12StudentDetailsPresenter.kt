package by.itacademy.pvt.dz12.mvp

import by.itacademy.pvt.dz12.Student
import by.itacademy.pvt.dz12.dz12Singleton

class Dz12StudentDetailsPresenter(private val idStudent: String?) {

    private var view: Dz12StudentDetailsView? = null
    private var user: Student? = null

    private fun removeStudent(idStudent: String) {
        dz12Singleton.getListStudent().find {
            it.id == idStudent
        }?.apply { dz12Singleton.getListStudent().remove(this) }
    }

    fun setView(view: Dz12StudentDetailsView) {
        this.view = view
    }

    fun onDestroy() {
        view = null
    }

    fun getStudentById() {
        user = idStudent?.let { dz12Singleton.getStudentById(it) }
        if (user == null) {
            view?.onError()
            view?.backStack()
        } else
            view?.showStudent(user!!)
    }

    fun deleteButtonWasClicked() {
        idStudent?.let { removeStudent(it) }
    }
}