package raum.muchbeer.paging3ktx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import raum.muchbeer.paging3ktx.repository.github.GithubRepository
import java.lang.IllegalArgumentException

class GitSearchViewModelFactory(val githubRepository: GithubRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GitSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GitSearchViewModel(githubRepository) as T
        }
        throw IllegalArgumentException("unknown view model")
    }
}