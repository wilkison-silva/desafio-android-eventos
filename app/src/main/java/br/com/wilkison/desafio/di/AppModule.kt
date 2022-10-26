package br.com.wilkison.desafio.di

import br.com.wilkison.desafio.BuildConfig
import br.com.wilkison.desafio.data.repository.EventRepositoryImpl
import br.com.wilkison.desafio.data.service.EventService
import br.com.wilkison.desafio.domain.repository.EventRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL_BASE = "http://5f5a8f24d44d640016169133.mockapi.io/api"

val retrofitModule = module {
    single<OkHttpClient> {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            client.addInterceptor(logging)
        }
        client.build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(get<OkHttpClient>())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

val serviceModule = module {
    single<EventService> { get<Retrofit>().create(EventService::class.java) }
}

val repositoryModule = module {
    single<EventRepository> {
        EventRepositoryImpl(get<EventService>())
    }
}