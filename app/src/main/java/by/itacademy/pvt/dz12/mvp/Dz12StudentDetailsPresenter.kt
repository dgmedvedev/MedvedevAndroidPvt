package by.itacademy.pvt.dz12.mvp

import by.itacademy.pvt.R
import by.itacademy.pvt.app.App
import by.itacademy.pvt.dz12.network.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12StudentDetailsPresenter(private val idStudent: String?) {

    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null

    private var view: Dz12StudentDetailsView? = null

    private val idNotFound = App.instance.getString(R.string.id_not_found)
    private val studentNotFound = App.instance.getString(R.string.student_not_found)

    fun setView(view: Dz12StudentDetailsView) {
        this.view = view
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

    fun deleteButtonWasClicked() {
        idStudent?.let { id ->
            disposable = repository
                .deleteStudentById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        } ?: view?.onError(idNotFound)
    }

    fun detach() {
        disposable?.dispose()
        view = null
    }
}