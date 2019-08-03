package by.itacademy.pvt.dz12.mvp

import by.itacademy.pvt.dz12.Dz12StudentData
import by.itacademy.pvt.dz12.Student

class Dz12StudentDetailsPresenter(private val idStudent: String?) {

    private var view: Dz12StudentDetailsView? = null
    private var user: Student? = null

    private fun removeStudent(idStudent: String) {
        Dz12StudentData.getListStudent().find {
            it.id == idStudent
        }?.apply { Dz12StudentData.deleteStudent(this) }
    }

    fun setView(view: Dz12StudentDetailsView) {
        this.view = view
    }

    fun onDestroy() {
        view = null
    }

    fun getStudentById() {
        user = idStudent?.let { Dz12StudentData.getStudentById(it) }
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