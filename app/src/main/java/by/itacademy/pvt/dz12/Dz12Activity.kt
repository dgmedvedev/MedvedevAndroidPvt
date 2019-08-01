package by.itacademy.pvt.dz12

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import by.itacademy.pvt.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12Activity : Activity() {

    //  private val observable = Observable.just("Hello","222","333","444")

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

/*
        disposable = observable
            .delay(3, TimeUnit.SECONDS)
         //   .throttleFirst(3, TimeUnit.SECONDS)// на сколько часто хотим получать данные после начала ввода
         //   .debounce {  } - на сколько часто хотим получать данные
            .filter{it !="444"} // не хочу получать "Hello"
      //      .map{it.toInt()} // конвертируем данные входящие в Int - не обязательный метод
            .subscribeOn(Schedulers.io()) // говорит в каком потоке выполнить (здесь в главном потоке)
            .observeOn(AndroidSchedulers.mainThread()) // говорит в каком потоке вернуть результат
            .subscribe({data->
            textView.text = data.toString()
            },{throwable->
                textView.text = throwable.toString()
            })
   //         .subscribe{data->  - этот вариант без обработки ошибок
   //             textView.text = data.toString()
   //         }
   */
        disposable = repository
            .getList(10, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                textView.text = data.toString()
                textViewId.text = data.find { it.id == "9D650B02-905D-679B-FF05-DAD9C1B32000" }.toString()
            }, { throwable ->
                textViewId.text = throwable.toString()
            })

        disposableId = repositoryId
            .getById("9D650B02-905D-679B-FF05-DAD9C1B32000")
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