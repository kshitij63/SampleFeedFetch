package network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by user on 12/29/2017.
 */

interface FeedApiInterface {
    @FormUrlEncoded
    @POST("fetchfeed")
    fun getAllFeedsFromNetwork(@Field("city") city: String,
                               @Field("batchSize") bacthSize: String,
                               @Field("publishedDate") publishedDate: String): io.reactivex.Observable<FeedModal>
}
