package by.itacademy.pvt.dz12

private val BASE_URL =
    "https://api.backendless.com/84E983D9-EC47-12EF-FF57-8CEA507D4900/465B3EE0-44E3-6CE7-FF84-FF25210F9900/"
private val BASE_URL_ID =
    "https://api.backendless.com/84E983D9-EC47-12EF-FF57-8CEA507D4900/465B3EE0-44E3-6CE7-FF84-FF25210F9900/"

fun provideStudentRepository(): StudentRepository {

    return StudentRepositoryRemote(
        NetProvider.provideStudentApi(
            NetProvider.provideRetrofit(
                BASE_URL,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}

fun provideStudentRepositoryByID(): StudentRepository {

    return StudentRepositoryRemote(
        NetProvider.provideStudentApi(
            NetProvider.provideRetrofit(
                BASE_URL_ID,
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}