package github.sdv.com.githubclient.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import github.sdv.com.githubclient.Constant
import github.sdv.com.githubclient.Constant.BASE_URL
import github.sdv.com.githubclient.di.PerApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @PerApplication
    @Provides
    fun provideCache(appContext: Application): Cache = Cache(appContext.cacheDir, Constant.CACHE_SIZE)

    @PerApplication
    @Provides
    fun provideHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .build()
    }

    @PerApplication
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}