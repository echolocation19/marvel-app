package com.example.marveltestapp

import android.app.Application
import androidx.work.Configuration
import com.example.marveltestapp.data.worker.RefreshDataWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var factory: RefreshDataWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(factory)
            .build()
    }

}