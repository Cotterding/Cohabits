package ovh.cohabits.cohabit1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ConnexionActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        //we need to use the permanent values stored in the app object
        //the getApplication() method is the running app object
        //as this mathod returns a native android.app.Application object,
        //we have to cast it to the subclass that we have defined
        //then we can read the attributes defined by the subclass
        val app = (getApplication() as cohabitsClass)

        //port selector
        //we need a way to specify to the app which server it should use
        //the user can click on the app logo on the first screen
        //the port number will change and be displayed briefly
        //the port number will cycle bettween 8080 and 8085
        //8080 is the "production" server
        //8081 to 8085 are the "developpment" servers

        //get the logo object from its ID (set in the layout XML file)
        val logo = findViewById<ImageView>(R.id.imageViewLogoConnexion)

        //add the callback function for a click on the logo
        logo.setOnClickListener {
            //change the port number
            app.httpPort += 1
            if (app.httpPort == 8087) {
                //After 8086, go back to 8080
                app.httpPort = 8080
            }

            //use a "toast" object to display the text of the active port number
            var toast: Toast = Toast.makeText(getApplicationContext(), app.httpPort.toString(), Toast.LENGTH_SHORT)
            //get down into the Toast object
            //because we want big characters displayed
            var layout: RelativeLayout = toast.view as RelativeLayout
            var tv: TextView = layout.getChildAt(0) as TextView
            //now we can set the characters size
            tv.setTextSize(30F)
            //and set the toast position on screen
            toast.setGravity(Gravity.TOP, 0, 200)
            //ok, time to display the toast now
            toast.show()
        }

        //button "Je n'ai pas de compte"
        val newButton = findViewById<Button>(R.id.button_noCompte)
        newButton.setOnClickListener {
            //launch the new account activity
            val intent = Intent(this, CreationCompteActivity::class.java)
            startActivity(intent)
        }

        //button "continuer"
        val logonButton = findViewById<Button>(R.id.button_connexion)
        logonButton.setOnClickListener {

            //get data from editText fields
            var email = findViewById<EditText>(R.id.editText_emailAddress_identification).getText().toString()
            var pwd = findViewById<EditText>(R.id.editText_Password_identification).getText().toString()

            //build the url string with server address and arguments
            var url = app.apiurl() + "student/connect?email=" + email + "&password=" + pwd

            //create the Volley request object
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                Response.Listener
                { response ->
                    //display the response string with a popup on wcreen
                    //todo: change activity is connection was successful
                    //todo: display the correct message if connection was refused
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show()
                    //print the response in the android studio trace window (when debugging)
                    println(response)
                },
                object : Response.ErrorListener {
                    //this is an object used only to add the onErrorResponse function on it
                    override fun onErrorResponse(error: VolleyError) {
                        //display the error in android studio trace window (when debugging)
                        if (error is com.android.volley.NoConnectionError) {
                            println("No internet connection")
                        } else if (error is com.android.volley.TimeoutError) {
                            println("TimeoutError")
                        } else if (error is com.android.volley.AuthFailureError) {
                            println("Please check your credentials")
                        } else if (error is com.android.volley.ServerError) {
                            println("Server is not responding. Please try again later")
                        } else if (error is com.android.volley.NetworkError) {
                            println("Please check your internet connection")
                        } else if (error is com.android.volley.ParseError) {
                            println("Parsing error! Please try again after some time")
                        } else if (error is com.android.volley.ParseError) {
                            println("Parsing error! Please try again after some time")
                        }
                    }
                })

            //now add the request in the Volley request queue
            //the Volley library will later call either :
            // - the Response.listener defined above if the request receives a response
            // - or the Response.errorListener defined above in case of error

            //we need here to add the question mark after the app variable name
            //because the queue attribute was defined as Nullable type "RequestQueue?"
            //here the question mark means : use the value if not null
            //otherwise do nothing (and never trigger a null pointer error)

            app.queue?.add(stringRequest)
        }
    }
}
