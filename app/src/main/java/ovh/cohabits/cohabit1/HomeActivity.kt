package ovh.cohabits.cohabit1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
        val cardView = findViewById<CardView>(R.id.card_view_box)
        cardView.setBackgroundResource(R.drawable.box)
        val button = findViewById<Button>(R.id.button_creer_colocation)
        button.setOnClickListener() {
            val intent = Intent(this, StatutFragment::class.java)
            startActivity(intent)
        }

    }
}