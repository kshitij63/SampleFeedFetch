package viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.omnify.hire.samplefeedfetch.MainActivity
import com.omnify.hire.samplefeedfetch.MyApplication

import data.FeedEntity
import repository.FeedFetchRepository
import javax.inject.Inject

/**
 * Created by user on 12/29/2017.
 */

 class FeedViewModel : ViewModel() {

    @Inject
  lateinit var feedFetchRepo: FeedFetchRepository


    fun getLiveData():LiveData<List<FeedEntity>>?{
    return feedFetchRepo.getLiveDataForFeedList()
}
    }
