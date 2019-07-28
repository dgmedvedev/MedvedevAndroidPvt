package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.Singleton
import by.itacademy.pvt.dz6.Student

class Dz11StudentDetailsPresenter(private val idStudent: Long?) {

    private var view: Dz11StudentDetailsView? = null
    private var user: Student? = null

    fun setView(view: Dz11StudentDetailsView) {
        this.view = view
    }

    fun getStudentById() {
        user = idStudent?.let { Singleton.getStudentById(it) }
        if (user == null) {
            view?.onError()
            view?.backStack()
        } else
            view?.showStudent(user!!)
    }

    fun deleteButtonWasClicked() {
        idStudent?.let { removeStudent(it) }
    }

    private fun removeStudent(idStudent: Long) {
        Singleton.getListStudent().find {
            it.id == idStudent
        }?.apply { Singleton.getListStudent().remove(this) }
    }
}