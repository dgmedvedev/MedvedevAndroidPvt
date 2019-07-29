package by.itacademy.pvt.dz11.mvp

import by.itacademy.pvt.dz6.Singleton

class Dz11StudentListPresenter {

    private var view: Dz11StudentListView? = null

    fun setView(view: Dz11StudentListView?) {
        this.view = view
    }

    fun load() {
        view?.showList(Singleton.getListStudent())
    }

    fun search(text: String) {
        view?.showList(Singleton.filter(text))
    }
}