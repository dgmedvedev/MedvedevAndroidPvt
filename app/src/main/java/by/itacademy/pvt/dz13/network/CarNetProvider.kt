package by.itacademy.pvt.dz13.network

import by.itacademy.pvt.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CarNetProvider {

    private var carApi: CarApi? = null

    fun provideGson(): Gson {
        val gson = GsonBuilder()
            .create()
        return gson
    }

    fun provideGsonExpose(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    fun provideOkHttp(): OkHttpClient { // это паттерн Builder, задаем ему параметры,
        // а он возвращает без ошибок нужную инфу

        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) { // сработает только в Debag режиме
            val loggin = HttpLoggingInterceptor() // задаем интерцептер - перехватывает запросы
            loggin.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(loggin)
        }

        // okHttpBuilder.connectTimeout(2, TimeUnit.SECONDS) - не обязательно
        // время ожидания ответа от сервера, время закончилось - ошибка

        val okHttpClient = okHttpBuilder.build()

        return okHttpClient
    }

    fun provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    fun provideApi(retrofit: Retrofit): CarApi {

        if (carApi == null) {
            carApi = retrofit.create<CarApi>(CarApi::class.java)
        }
        return carApi!!
    }
}