package org.mathieu.data.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import org.mathieu.data.repositories.CharacterRepositoryImpl
import org.mathieu.data.repositories.LocationRepositoryImpl
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.domain.repositories.LocationRepository

//https://rickandmortyapi.com/documentation/#rest
private const val RMAPI_URL = "https://rickandmortyapi.com/api/"

val dataModule = module {

    single<HttpClient> {
        org.mathieu.data.remote.createHttpClient(
            baseUrl = RMAPI_URL
        )
    }

    single<org.mathieu.data.local.RealmDatabase> { org.mathieu.data.local.RMDatabase() }

    single { org.mathieu.data.local.CharacterLocal(get()) }

    single { org.mathieu.data.remote.CharacterApi(get()) }

    single<CharacterRepository> {
        CharacterRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    }

    single { org.mathieu.data.local.LocationLocal(get()) }

    single { org.mathieu.data.remote.LocationAPI(get()) }

    single<LocationRepository> {
        LocationRepositoryImpl(
            get(),
            get(),
            get(),
        )
    }

}