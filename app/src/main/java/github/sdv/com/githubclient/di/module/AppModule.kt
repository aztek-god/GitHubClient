package github.sdv.com.githubclient.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import github.sdv.com.githubclient.App
import github.sdv.com.githubclient.di.PerApplication


@Module
class AppModule(private val appContext: App) {
    @PerApplication
    @Provides
    fun provideAppContext(): Application = appContext
}