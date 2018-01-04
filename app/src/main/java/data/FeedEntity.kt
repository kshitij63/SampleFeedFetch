package data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

/**
 * Created by user on 12/29/2017.
 */
@Entity
data class FeedEntity(@PrimaryKey var _id: String,var title:String,var description:String)



