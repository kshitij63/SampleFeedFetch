package network

import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 12/29/2017.
 */

object FeedRetrofit {

    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl("https://soapi.in:3001/feed/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}
