package by.itacademy.pvt.dz13.network

fun provideCarRepository(): CarRepository {

    return CarRepositoryRemote(
        CarNetProvider.provideApi(
            CarNetProvider.provideRetrofit(
                "http://kiparo.ru/",
                CarNetProvider.provideOkHttp(),
                CarNetProvider.provideGson()
            )
        )
    )
}