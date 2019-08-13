package by.itacademy.pvt.dz12.mvp

import by.itacademy.pvt.dz12.Callback
import by.itacademy.pvt.dz12.Student
import by.itacademy.pvt.dz12.network.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12StudentListPresenter {
    private val pageSize = 100
    private val offset = 0

    private var listStudents: MutableList<Student> = mutableListOf()

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    private var view: Dz12StudentListView? = null

    private fun loadStudentList(callback: Callback) {
        disposable = repository
            .getList(pageSize, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                listStudents = it
                callback.returnResult()
            }
    }

    fun setView(view: Dz12StudentListView?) {
        this.view = view
    }

    fun load(): List<Student> {

        if (listStudents.isEmpty()) {
            view?.progressBarOn()
            loadStudentList(object : Callback() {
                override fun returnResult() {
                    view?.progressBarOff()
                    view?.updateDatabase()
                }
            })
        }
        return listStudents
    }

    private fun filter(search: String): List<Student> {
        return listStudents.filter { it.name.toUpperCase().contains(search.toUpperCase()) }
    }

    fun search(text: String) {
        view?.showList(filter(text))
    }

    fun detach() {
        disposable?.dispose()
        view = null
    }
}