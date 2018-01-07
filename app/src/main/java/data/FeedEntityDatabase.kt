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

    abstract fun getDao(): FeedDao


}
