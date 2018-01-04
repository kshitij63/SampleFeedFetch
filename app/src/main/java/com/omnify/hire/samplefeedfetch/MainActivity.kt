package com.omnify.hire.samplefeedfetch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.util.Log
import dagger.DaggerMainActivityComponent
import dagger.MainActivityModule
import data.FeedEntity
import network.FeedApiInterface
import retrofit2.Retrofit
import viewModels.FeedViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var feedView: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedView=ViewModelProviders.of(this).get(FeedViewModel::class.java)


        feedView.getLiveData()?.observe(this, object : Observer<List<FeedEntity>> {
            override fun onChanged(@Nullable t: List<FeedEntity>?) {
                Log.e("size of list", "${t!!.size}")
                for (i in t!!.indices) {
                    Log.e("from activity", t?.get(i)?.title)


                }
            }

        })
    }
}
