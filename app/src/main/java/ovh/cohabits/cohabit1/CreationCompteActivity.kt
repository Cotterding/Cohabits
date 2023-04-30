package ovh.cohabits.cohabit1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class CreationCompteActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creation_compte)

        //get the app object as an instance of cohabits subclass
        val app = (getApplication() as cohabitsClass)

        val backButton = findViewById<Button>(R.id.button_retour_connexion)
        backButton.setOnClickListener {
            val intent = Intent(this, ConnexionActivity::class.java)
            startActivity(intent)
        }

        val newButton = findViewById<Button>(R.id.button_valide_new_compte)
        newButton.setOnClickListener {

            //get data from editText fields
            var pseudo = findViewById<EditText>(R.id.editText_Speudo).getText().toString()
            var email = findViewById<EditText>(R.id.editText_emailAddress_creation).getText().toString()
            var pwd = findViewById<EditText>(R.id.ditText_Password_creation).getText().toString()

            //todo: check that both passwords are equal and display error if not
            //todo: enforce password security (length and character types)

            //build the url string with server address and arguments
            var url= app.apiurl() + "student/create?nickname=" + pseudo + "&email=" + email + "&password=" + pwd

            //create the Volley request object
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener
                { response ->
                    // Display the response string.
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show()
                    println(response)
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError) {
                        if (error is NoConnectionError) {
                            println("No internet connection")
                        } else if (error is TimeoutError) {
                            println("TimeoutError")
                        } else if (error is AuthFailureError) {
                            println("Please check your credentials")
                        } else if (error is ServerError) {
                            println("Server is not responding. Please try again later")
                        } else if (error is NetworkError) {
                            println("Please check your internet connection")
                        } else if (error is ParseError) {
                            println("Parsing error! Please try again after some time")
                        } else if (error is ParseError) {
                            println("Parsing error! Please try again after some time")
                        }
                    }
                })
            //now push the request object in the Volley queue
            app.queue?.add(stringRequest)
        }
    }
}