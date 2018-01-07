package viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.omnify.hire.samplefeedfetch.MyApplication

import data.FeedEntity
import repository.FeedFetchRepository
import javax.inject.Inject

/**
 * Created by user on 12/29/2017.
 */

class FeedViewModel(application: Application) : AndroidViewModel(application) {
    init {
        MyApplication.component.inject(this)
    }

    @Inject
    lateinit var feedFetchRepository: FeedFetchRepository


    fun getLiveData(): LiveData<List<FeedEntity>>? {
        return feedFetchRepository.getLiveDataForFeedList()
    }
}
