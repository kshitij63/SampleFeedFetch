package repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.google.gson.Gson

import data.FeedDao
import data.FeedEntity
import data.FeedEntityDatabase
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import network.FeedApiInterface
import network.FeedModal
import network.FeedRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by user on 12/29/2017.
 */

class FeedFetchRepository constructor(context: Context) {
    var con = context

    private var liveDataForFeedList: LiveData<List<FeedEntity>>? = null
    private var feedEntityDatabase: FeedEntityDatabase? = null
    private var instance: Retrofit? = null
    private var feedApiInterface: FeedApiInterface? = null
    private var listOfFeedEntities: io.reactivex.Observable<List<FeedEntity>>? = null;

    fun getDatafromNetwork():Observable<FeedModal>{
        instance = FeedRetrofit.getRetrofit()
        feedApiInterface = instance!!.create(FeedApiInterface::class.java)
        return feedApiInterface!!.getAllFeedsFromNetwork("delhi", "10", "1513154868810")


    }

    fun getLiveDataForFeedList() // gets feed from database return instance of livedata
            : LiveData<List<FeedEntity>> {
        Log.e("tag", "in getLiveDataForFeedList")

        feedEntityDatabase = FeedEntityDatabase.getInstance(con)
        val feedDao = feedEntityDatabase!!.feedDao
        liveDataForFeedList = feedDao.top10FeedEntities
        instance = FeedRetrofit.getRetrofit()

        feedApiInterface = instance!!.create(FeedApiInterface::class.java)
        //modal =
                feedApiInterface!!.getAllFeedsFromNetwork("delhi", "10", "1513154868810")
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io()).subscribe(object:Observer<FeedModal>{
                    override fun onNext(t: FeedModal) {
                        var list= t.data.feed
                        for (i in list?.indices!!) {
                            Log.e("tag", list.get(i).title)

                            feedDao.insertSingleFeed(list.get(i))

                        }
                      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                    }

                    override fun onComplete() {
                    //    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(e: Throwable) {
                  //      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onSubscribe(d: Disposable) {
                //        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })

        /*modal!!.enqueue(object : Callback<FeedModal> {
            override fun onResponse(call: Call<FeedModal>?, response: Response<FeedModal>?) {
                Log.e("application",  Gson().toJson(response))

                Log.e("tag", "hereeeee")
                var list = response?.body()?.data?.feed

                for (i in list?.indices!!) {


                }
            }

            override fun onFailure(call: Call<FeedModal>?, t: Throwable?) {
                if (t != null) {
                    Log.e("tag error",t.localizedMessage)
                }

            }

        })
*/

        return feedDao.allFeedEntities

    }

}
