package com.omnify.hire.samplefeedfetch

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import data.FeedEntity
import viewModels.FeedViewModel

/**
 * Created by user on 12/29/2017.
 */

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.liveDataOfFeedFromViewModel.observe(this, Observer { })
    }
}
