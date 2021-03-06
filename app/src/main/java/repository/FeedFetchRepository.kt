package repository

import android.arch.lifecycle.LiveData
import android.util.Log

import data.FeedDao
import data.FeedEntity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import network.FeedApiInterface
import network.FeedModal
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by user on 12/29/2017.
 */

class FeedFetchRepository @Inject constructor(var instance: Retrofit, var feedDao: FeedDao) {


    fun getLiveDataForFeedList() // gets feed from database return instance of livedata
            : LiveData<List<FeedEntity>>? {
        Log.e("tag", "in getLiveDataForFeedList")

        var feedApiInterface = instance!!.create(FeedApiInterface::class.java)
        feedApiInterface!!.getAllFeedsFromNetwork("delhi", "10", "1513154868810")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(object : Observer<FeedModal> {
            override fun onNext(t: FeedModal) {
                var list = t.data.feed
                for (i in list?.indices!!) {

                    feedDao?.insertSingleFeed(list.get(i))

                }

            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
            }

            override fun onSubscribe(d: Disposable) {
            }
        })


        return feedDao?.allFeedEntities

    }

}
