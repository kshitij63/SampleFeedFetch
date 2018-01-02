package data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by user on 12/29/2017.
 */
@Entity
class FeedEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var title: String? = null
    var description: String? = null
    var imageUrl: String? = null
    var createdBy: String? = null


}
