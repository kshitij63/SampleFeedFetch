package com.omnify.hire.samplefeedfetch

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
//import jdk.nashorn.internal.runtime.ECMAException.getException
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.*
import com.google.gson.JsonObject
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main2.*
import network.UserProfileD
import network.profileDetailApiInteface
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class Main2Activity : AppCompatActivity() {
    @Inject
    lateinit var profile: profileDetailApiInteface

    @Inject
lateinit var gso:GoogleSignInOptions

    val RC_SIGN_IN = 100

    @Inject
    lateinit var mFirebaseAuth: FirebaseAuth

    lateinit var callBackManager:CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        MyApplication.component.injectProfile(this)
         callBackManager=CallbackManager.Factory.create()
        //login_button.setReadPermissions("email","public_profile")
        login_button.registerCallback(callBackManager,object:FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
               Log.e("tag","success ${result?.accessToken}")
                handleFacebookAccessToken(result?.accessToken);


                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancel() {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(error: FacebookException?) {
                Log.e("tag",error?.message)

                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        )

        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInBut.setOnClickListener {
            signIn(mGoogleSignInClient)
        }

    }

    private fun signIn(mGoogleApiClient: GoogleSignInClient) {
        val signInIntent = mGoogleApiClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            var account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account);
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        val user = mFirebaseAuth.getCurrentUser()
                        Log.e("meta data", user?.metadata.toString())
                        Log.e("id", user?.providerId)
                        Log.e("uid", acct.id)

                        profile.getProfile(id = acct.id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                                .subscribe(object : Observer<UserProfileD> {
                                    override fun onComplete() {

                                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                    }

                                    override fun onError(e: Throwable) {
                                        Log.e("error", e.message)
                                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                    }

                                    override fun onNext(t: UserProfileD) {
                                        Log.e("genders is", t.genders.get(0).value)
                                        Log.e("coverphoyto ",t.coverPhotos.get(0).url)
                                        Log.e("photo",t.photos.get(0).url)
                                        Log.e("email",acct.email)
                                        Log.e("name",acct.displayName)


                                        var jsonObject=JSONObject()
                                        jsonObject.put("gender",t.genders.get(0).value)
                                        jsonObject.put("name",acct.displayName.toString())
                                        jsonObject.put("id",acct.email)
                                        jsonObject.put("profilePicture",t.photos.get(0).url)
                                        jsonObject.put("coverPicture",t.coverPhotos.get(0).url)
                                        jsonObject.put("email",acct.email)

getDataFromVolley(jsonObject,"google")
            /*                            getDataFromVolley(acct.id.toString(),t.genders.get(0).value,acct.displayName.toString(),
                                                acct.email.toString(),t.photos.get(0).url,t.coverPhotos.get(0).url,"google")
            */                             }

                                    override fun onSubscribe(d: Disposable) {
                                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                    }
                                })



                        Toast.makeText(this, user?.metadata.toString(), Toast.LENGTH_SHORT).show()
                        //updateUI(user)
                    } else {
                    }

                })
    }

    private fun handleFacebookAccessToken(token:AccessToken?){
        var credentials=FacebookAuthProvider.getCredential(token!!.token)
        mFirebaseAuth.signInWithCredential(credentials)
                .addOnCompleteListener(this, object:OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                    //    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    if(p0.isSuccessful){
                        var request=GraphRequest.newMeRequest(token,object:GraphRequest.GraphJSONObjectCallback{
                            override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {
                                Log.e("tag,response",response.toString())
                                Log.e("tag,json",`object`.toString())

                               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }
                        })
                        var bundel=Bundle()
                        bundel.putString("fields","id,name,gender,email,cover,picture.type(large)")
                        request.parameters=bundel
                        request.executeAsync()
                        Log.e("tag","successfully logged In")
                    }
                        else{
                        Log.e("tag",p0.exception.toString())
                    }
                    }

                })
    }
    
    fun getDataFromVolley(jsonObject: JSONObject,mode:String){
        var url="https://s-o.co.in:1304/users/registerNew/${mode}"
        var queue=Volley.newRequestQueue(this)
        var request=object:JsonObjectRequest(url,jsonObject,com.android.volley.Response.Listener<JSONObject>{
            response ->
            Log.e("RESPONSE",response.toString())
        },com.android.volley.Response.ErrorListener {
            error ->
            Log.e("ERROR",error.message)
            Log.e("URL",url)


        }){
            override fun getHeaders(): MutableMap<String, String> {
                var map=HashMap<String,String>()
                map.put("Content-Type","application/json")
                return  map
            }
        } /*{
            override fun getParams(): MutableMap<String, String> {
                var map = HashMap<String, String>()
                map.put("id", id)
                map.put("gender", gender)
                map.put("displayName", displayName)
                map.put("email", email)
                map.put("profilePicture", profilePicture)
                map.put("coverPicture", coverPicture)
                return map

            }*/
        //}

        queue.add(request)



    }


}
