package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.servicelocator.appDataBaseModule
import com.example.rickandmorty.servicelocator.networkModule
import com.example.rickandmorty.servicelocator.repositoryModule
import com.example.rickandmorty.servicelocator.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, repositoryModule, viewModelModule, appDataBaseModule)
        }
    }
}