package br.com.wilkison.desafio.di

import br.com.wilkison.desafio.BuildConfig
import br.com.wilkison.desafio.data.repository.EventRepositoryImpl
import br.com.wilkison.desafio.data.service.EventService
import br.com.wilkison.desafio.domain.repository.EventRepository
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCase
import br.com.wilkison.desafio.domain.usecases.get_event_details.GetEventDetailsUseCaseImpl
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCase
import br.com.wilkison.desafio.domain.usecases.get_events_list.GetEventsListUseCaseImpl
import br.com.wilkison.desafio.presentation.feature.list_events.ListEventsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL_BASE = "http://5f5a8f24d44d640016169133.mockapi.io/api/"

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

val useCaseModule = module {
    single<GetEventsListUseCase> {
        GetEventsListUseCaseImpl(get<EventRepository>())
    }
    single<GetEventDetailsUseCase> {
        GetEventDetailsUseCaseImpl(get<EventRepository>())
    }
}

val viewModelModule = module {
    viewModel<ListEventsViewModel> {
        ListEventsViewModel(get<GetEventsListUseCase>())
    }
}