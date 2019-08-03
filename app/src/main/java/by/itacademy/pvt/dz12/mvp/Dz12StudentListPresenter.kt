package by.itacademy.pvt.dz12.mvp

import by.itacademy.pvt.dz12.Callback
import by.itacademy.pvt.dz12.Dz12StudentData
import by.itacademy.pvt.dz12.Student

class Dz12StudentListPresenter {

    private var view: Dz12StudentListView? = null

    fun setView(view: Dz12StudentListView?) {
        this.view = view
    }

    fun load(): List<Student> {
        val studentList = Dz12StudentData.getListStudent()
        if (studentList.isEmpty()) {
            view?.progressBarOn()
            Dz12StudentData.loadStudentList(object : Callback() {
                override fun returnResult() {
                    view?.progressBarOff()
                    view?.updateDatabase()
                }
            })
        }
        return studentList
    }

    fun search(text: String) {
        view?.showList(Dz12StudentData.filter(text))
    }

    fun detach() {
        Dz12StudentData.dispose()
        view = null
    }
}