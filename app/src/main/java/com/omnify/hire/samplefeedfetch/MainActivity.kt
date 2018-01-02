package com.omnify.hire.samplefeedfetch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import data.FeedEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import network.FeedModal
import viewModels.FeedViewModel
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var feedView = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        //if(feedView.liveDataOfFeedFromViewModel.)
        feedView.liveDataOfFeedFromViewModel.observeForever( object : Observer<List<FeedEntity>> {
            override fun onChanged(@Nullable t: List<FeedEntity>?) {
                Log.e("tag","out OF LOOP..YEAAHH")

                Toast.makeText(this@MainActivity, t?.get(0)?.title, Toast.LENGTH_SHORT).show()

            }

        })
    }
}
