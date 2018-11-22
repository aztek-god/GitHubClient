package github.sdv.com.githubclient

import android.app.Application
import github.sdv.com.githubclient.di.AppComponent
import github.sdv.com.githubclient.di.DaggerAppComponent
import github.sdv.com.githubclient.di.module.AppModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}