package com.example.womansaftey

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterComplaintActivity : AppCompatActivity() {

    private lateinit var etYourName: EditText
    private lateinit var etIncidentDetails: EditText
    private lateinit var etComplaintEmail: EditText
    private lateinit var btnSubmitComplaint: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_complaint)

        etYourName = findViewById(R.id.etYourName)
        etIncidentDetails = findViewById(R.id.etIncidentDetails)
        etComplaintEmail = findViewById(R.id.etComplaintEmail)
        btnSubmitComplaint = findViewById(R.id.btnSubmitComplaint)

        btnSubmitComplaint.setOnClickListener {
            sendComplaintEmail()
        }
    }

    private fun sendComplaintEmail() {
        val name = etYourName.text.toString().trim()
        val details = etIncidentDetails.text.toString().trim()
        val emailTo = etComplaintEmail.text.toString().trim()

        if (name.isEmpty() || details.isEmpty() || emailTo.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val subject = "Harassment Complaint from $name"
        val body = """
            Name: $name

            Incident Details:
            $details
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailTo))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No email app found.", Toast.LENGTH_SHORT).show()
        }
    }
}
