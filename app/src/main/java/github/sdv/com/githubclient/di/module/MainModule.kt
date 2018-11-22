package github.sdv.com.githubclient.di.module

import dagger.Module
import dagger.Provides
import github.sdv.com.githubclient.di.PerActivity
import github.sdv.com.githubclient.model.repository.GitHubRepository
import github.sdv.com.githubclient.ui.vm.MainViewModel
import retrofit2.Retrofit

@Module
class MainModule {
    @Provides
    @PerActivity
    fun provideGithubRepository(retrofit: Retrofit): GitHubRepository {
        return GitHubRepository(retrofit)
    }

    @Provides
    @PerActivity
    fun provideViewModel(repository: GitHubRepository): MainViewModel {
        return MainViewModel(repository)
    }
}