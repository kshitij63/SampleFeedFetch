package data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.omnify.hire.samplefeedfetch.MyApplication

/**
 * Created by user on 12/29/2017.
 */
@Database(entities = arrayOf(FeedEntity::class), version = 1)
abstract class FeedEntityDatabase : RoomDatabase() {

   abstract fun getDao():FeedDao
    companion object {
        lateinit  var instance: FeedEntityDatabase

        fun getInstance(context: MyApplication): FeedEntityDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, FeedEntityDatabase::class.java,"feed_db").build()
                Log.e("tag","places")
            }

            return instance
        }
    }
}
