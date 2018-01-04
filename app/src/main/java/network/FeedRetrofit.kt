package network

import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Retrofit
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 12/29/2017.
 */

class FeedRetrofit {

        lateinit  var retrofit:Retrofit



    fun getRetrofitInit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl("some url")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}
