package dagger

import android.arch.lifecycle.ViewModelProviders
import com.omnify.hire.samplefeedfetch.MainActivity
import com.omnify.hire.samplefeedfetch.MyApplication
import data.FeedDao
import data.FeedEntityDatabase
import network.FeedRetrofit
import repository.FeedFetchRepository
import retrofit2.Retrofit

import javax.inject.Singleton

/**
 * Created by user on 1/3/2018.
 */
@Module()
class MainActivityModule(var myApplication:MyApplication) {



@Provides
@CustomScope
    fun getRetrofit():Retrofit
    {
        return FeedRetrofit().getRetrofitInit()
    }


    @Provides
    @CustomScope
    fun getFeedRepository():FeedFetchRepository{
        return FeedFetchRepository()
    }

@Provides
@CustomScope

        fun getFeedFetchDataBaseDao(): FeedDao {
        return FeedEntityDatabase.getInstance(myApplication).getDao()


    }



}