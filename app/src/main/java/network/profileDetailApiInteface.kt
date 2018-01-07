package network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
 * Created by user on 1/5/2018.
 */

interface profileDetailApiInteface {

    @GET("people/{id}/?personFields=genders,coverPhotos,photos&key=AIzaSyDE6d3IXCoKnfVG-pvg0RG0JYTKS6eo1VA")
     fun getProfile(@Path("id") id:String?):Observable<UserProfileD>
}