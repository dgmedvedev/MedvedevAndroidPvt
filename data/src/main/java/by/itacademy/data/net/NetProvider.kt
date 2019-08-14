package by.itacademy.data.net

import by.itacademy.data.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetProvider {

    private var api: Api? = null

    fun provideGson(): Gson {
        val gson = GsonBuilder()
            .create()

        return gson
    }

    fun provideOkHttp(): OkHttpClient {

        val okhttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            okhttpBuilder.addInterceptor(logging)
        }

        okhttpBuilder.connectTimeout(5, TimeUnit.SECONDS) // Ожидание ответа от сервера. Если время кончилось, то ошибка

        val okhttpClient = okhttpBuilder.build()

        return okhttpClient
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

    fun provideApi(retrofit: Retrofit): Api {
        if (api == null) {
            api = retrofit.create<Api>(Api::class.java)
        }
        return api!!
    }

    fun providePreparedApi(): Api {
        if (api == null) {
            val retrofit = provideRetrofit(
                baseUrl = "https://",
                okHttpClient = provideOkHttp(),
                gson = provideGson()
            )
            api = retrofit.create<Api>(Api::class.java)
        }
        return api!!
    }
}