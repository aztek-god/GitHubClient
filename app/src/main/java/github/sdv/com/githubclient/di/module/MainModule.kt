package github.sdv.com.githubclient.di.module

import dagger.Module
import dagger.Provides
import github.sdv.com.githubclient.di.PerActivity
import github.sdv.com.githubclient.model.repository.GitHubRepository
import retrofit2.Retrofit

@Module
class MainModule {
    @Provides
    @PerActivity
    fun provideGithubRepository(retrofit: Retrofit): GitHubRepository {
        return GitHubRepository(retrofit)
    }
}