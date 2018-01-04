package dagger

import android.app.Application
import com.omnify.hire.samplefeedfetch.MainActivity
import com.omnify.hire.samplefeedfetch.MyApplication
import javax.inject.Scope

/**
 * Created by user on 1/3/2018.
 */
@CustomScope
@Component(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun injectMainActivity(application: MyApplication )
}