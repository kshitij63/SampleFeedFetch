package viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context

import data.FeedEntity
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import network.FeedModal
import repository.FeedFetchRepository
import java.util.*

/**
 * Created by user on 12/29/2017.
 */

 class FeedViewModel constructor(application:Application) : AndroidViewModel(application) {
var cot=application as Context


    val liveDataOfFeedFromViewModel: LiveData<List<FeedEntity>>
        get() = FeedFetchRepository(cot).getLiveDataForFeedList()

    /*fun getDataFromNetworkViewModel():List<FeedEntity>{
           FeedFetchRepository(cot).getDatafromNetwork().observeOn(Schedulers.io())
                   .subscribeOn(Schedulers.io())
                   .subscribe(object: Observable<FeedModal> {

                   })
    }*/
}
