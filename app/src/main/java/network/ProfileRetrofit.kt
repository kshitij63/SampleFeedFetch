package network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 1/5/2018.
 */
class ProfileRetrofit {

    //lateinit var retrofit: Retrofit

fun getProfileRetrofit():Retrofit{
    return Retrofit.Builder().baseUrl(" https://people.googleapis.com/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
}