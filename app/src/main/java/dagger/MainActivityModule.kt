package dagger

import android.arch.persistence.room.Room
import android.content.Context
import android.provider.Settings.Global.getString
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.omnify.hire.samplefeedfetch.MyApplication
import com.omnify.hire.samplefeedfetch.R
import data.FeedDao
import data.FeedEntityDatabase
import network.ProfileRetrofit
import network.profileDetailApiInteface
import repository.FeedFetchRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by user on 1/3/2018.
 */
@Module
class MainActivityModule(var myApplication: MyApplication) {

    @Provides
    @CustomScope
    fun getFeedRepository(retrofit: Retrofit, feedDao: FeedDao): FeedFetchRepository {
        return FeedFetchRepository(retrofit, feedDao)
    }

    @Provides
    @CustomScope
    fun getFeedFetchDataBaseDao(database: FeedEntityDatabase): FeedDao {
        return database.getDao()
    }

    @Provides
    @CustomScope
    fun getRetrofitMod(): Retrofit {
        return Retrofit.Builder().baseUrl("https://soapi.in:3001/feed/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @CustomScope
    fun getFeedDatabase(): FeedEntityDatabase {
        return Room.databaseBuilder(myApplication, FeedEntityDatabase::class.java, "feed_db").build()

    }

    @Provides
    @CustomScope
    fun getProfileInterface(): profileDetailApiInteface {

        return ProfileRetrofit().getProfileRetrofit().create(profileDetailApiInteface::class.java)
    }

    @Provides
    @CustomScope
    fun getFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @CustomScope
    fun getGoogleSignOption():GoogleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(myApplication.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

}