package com.example.womansaftey

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnShareLocation: Button = findViewById(R.id.btnShareLocation)
        val btnPoliceContacts: Button = findViewById(R.id.btnPoliceContacts)
        val btnRegisterComplaint: Button = findViewById(R.id.btnRegisterComplaint)

        btnShareLocation.setOnClickListener {
            startActivity(Intent(this, ShareLocationActivity::class.java))
        }

        btnPoliceContacts.setOnClickListener {
            startActivity(Intent(this, PoliceContactsActivity::class.java))
        }

        btnRegisterComplaint.setOnClickListener {
            startActivity(Intent(this, RegisterComplaintActivity::class.java))
        }
    }
}
