package raum.muchbeer.paging3ktx

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import raum.muchbeer.paging3ktx.api.GitInstance
import raum.muchbeer.paging3ktx.db.repo.RepoDatabase
import raum.muchbeer.paging3ktx.repository.github.GithubRepository
import raum.muchbeer.paging3ktx.viewmodel.GitSearchViewModelFactory

object Injection {

    private fun provideGithubRepository(context: Context): GithubRepository {
        return GithubRepository(GitInstance.create(), RepoDatabase.getInstance(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return GitSearchViewModelFactory(provideGithubRepository(context))
    }
}