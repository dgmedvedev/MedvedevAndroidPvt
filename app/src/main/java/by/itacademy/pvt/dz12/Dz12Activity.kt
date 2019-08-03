package by.itacademy.pvt.dz12

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz12.network.provideStudentRepository
import by.itacademy.pvt.dz12.network.provideStudentRepositoryByID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12Activity : Activity() {

    private val repository = provideStudentRepository()
    private val repositoryId = provideStudentRepositoryByID()

    private var disposable: Disposable? = null
    private var disposableId: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz12)

        val textView = findViewById<TextView>(R.id.textView)
        val textViewId = findViewById<TextView>(R.id.textViewId)
        val textViewId2 = findViewById<TextView>(R.id.textViewId2)

        val idStudent = "BB32B1CB-C51F-B5E8-FFB1-9C91A0711D00"

        disposable = repository
            .getList(10, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                textView.text = data.toString()
                textViewId.text = data.find { it.id == idStudent }.toString()
            }, { throwable ->
                textViewId.text = throwable.toString()
            })

        disposableId = repositoryId
            .getById(idStudent)
            // .filter{it.id == idStudent}
            // .map{it.toInt()} // конвертируем входящие данные в Int - не обязательный метод
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                textViewId2.text = data.toString()
            }, { throwable ->
                textViewId2.text = throwable.toString()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        disposableId?.dispose()
    }
}