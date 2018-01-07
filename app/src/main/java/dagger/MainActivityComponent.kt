package dagger

import android.app.Activity
import android.app.Application
import com.omnify.hire.samplefeedfetch.Main2Activity
import com.omnify.hire.samplefeedfetch.MainActivity
import com.omnify.hire.samplefeedfetch.MyApplication
import viewModels.FeedViewModel
import javax.inject.Scope

/**
 * Created by user on 1/3/2018.
 */
@CustomScope
@Component(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(viewModel: FeedViewModel)
    fun injectProfile(activity: Main2Activity)
}