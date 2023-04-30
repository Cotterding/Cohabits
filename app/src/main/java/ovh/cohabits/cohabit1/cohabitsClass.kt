package ovh.cohabits.cohabit1

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.Volley.newRequestQueue

//we need to subclass the android native Application class
//because we need to store permanent data
//a permanent data should stay alive during the whole life of the application
//we cannot put such data in an Activity object
//because the activity is destroyed when the user switches to another screen

//we have to tell android to instantiate this subclass when launching the app
//this is done in the AndroidManifest.xml file
//see the application tag parameter : android:name

class cohabitsClass() : android.app.Application() {

    //the request queue will manage HTTP request
    //this is a part of the Volley library
    //the Volley library is the high-level library for HTTP requests
    //for example, Volley will retry any aborted HTTP request
    //without notifying the caller code

    //we declare here the queue field for the app object
    //but we cannot initialize it here
    //because when the app is launched, the "context" parameter is not yet allocated
    //and this "context" is the argument of the newRequestQueue method

    //therefore we set the queue to null
    //as the queue type is RequestQueue, null is not allowed for its value
    //so we use the Null safety mechanism of kotlin
    //see https://sebhastian.com/kotlin-question-mark/
    var queue: RequestQueue? = null

    //the app will connect on the default server on port 8080
    //or any development server from 8081 to 8085
    //this value is the active port number
    //it may be changed by clicking on the app logo in the connection Activity
    var httpPort: Int = 8080

    //cohabits backend server IP address
    val serveraddr = "51.38.238.103"

    //this method is called by kotlin when creating the app object
    //we override its definition for the subclass cohabitsClass
    override fun onCreate() {

        //we first call the superclass method
        //this will execute all the initialisation stuff of native android Application class
        super.onCreate()

        //now the app object is ready to use
        //that means the app object is a valid "context"
        //and we can use it to create the Volley request queue
        //and store the queue in the dedicated field
        this.queue = newRequestQueue(this)
    }

    //this method will return the API url
    //use it by appending the API message and its parameters as URL parameters
    fun apiurl(): String {
        return "http://" + serveraddr + ":" + httpPort + "/"
    }
}