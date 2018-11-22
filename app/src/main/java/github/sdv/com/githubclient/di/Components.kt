package github.sdv.com.githubclient.di

import android.app.Application
import dagger.Component
import github.sdv.com.githubclient.di.module.AppModule
import github.sdv.com.githubclient.di.module.MainModule
import github.sdv.com.githubclient.di.module.RetrofitModule
import github.sdv.com.githubclient.ui.MainActivity
import retrofit2.Retrofit

@PerApplication
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent {
    fun appContext(): Application
    fun retrofit(): Retrofit
}

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}