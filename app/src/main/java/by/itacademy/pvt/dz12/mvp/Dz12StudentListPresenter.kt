package by.itacademy.pvt.dz12.mvp

import by.itacademy.pvt.dz12.dz12Singleton

class Dz12StudentListPresenter {

    private var view: Dz12StudentListView? = null

    fun setView(view: Dz12StudentListView?) {
        this.view = view
    }

    fun load() {
        view?.showList(dz12Singleton.getListStudent())
    }

    fun search(text: String) {
        view?.showList(dz12Singleton.filter(text))
    }
}