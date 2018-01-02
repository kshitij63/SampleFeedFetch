package data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by user on 12/29/2017.
 */
@Dao
interface FeedDao {

    @get:Query("select * from FeedEntity")
    val allFeedEntities: LiveData<List<FeedEntity>>

    @get:Query("select * from FeedEntity limit 10")
    val top10FeedEntities: LiveData<List<FeedEntity>>

    @Insert
    fun insertSingleFeed(entity: FeedEntity)


}
