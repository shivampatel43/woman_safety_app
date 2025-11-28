package com.example.womansaftey

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class ShareLocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var etGuardianEmail: EditText
    private lateinit var tvLocationResult: TextView
    private lateinit var btnSendLocation: Button

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocationAndSendEmail()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_location)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        etGuardianEmail = findViewById(R.id.etGuardianEmail)
        tvLocationResult = findViewById(R.id.tvLocationResult)
        btnSendLocation = findViewById(R.id.btnSendLocation)

        btnSendLocation.setOnClickListener {
            val email = etGuardianEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter guardian email", Toast.LENGTH_SHORT).show()
            } else {
                checkLocationPermissionAndProceed()
            }
        }
    }

    private fun checkLocationPermissionAndProceed() {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                getLocationAndSendEmail()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                Toast.makeText(this, "Location permission is needed to send your location.", Toast.LENGTH_LONG).show()
                locationPermissionLauncher.launch(permission)
            }
            else -> {
                locationPermissionLauncher.launch(permission)
            }
        }
    }

    private fun getLocationAndSendEmail() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val locationText = "Lat: $latitude, Lng: $longitude"
                    tvLocationResult.text = "Current Location: $locationText"

                    sendLocationEmail(latitude, longitude)
                } else {
                    Toast.makeText(this, "Unable to get location. Try again.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error getting location: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun sendLocationEmail(lat: Double, lng: Double) {
        val guardianEmail = etGuardianEmail.text.toString().trim()
        val subject = "Emergency! Please Help Me"
        val mapsLink = "https://www.google.com/maps/search/?api=1&query=$lat,$lng"
        val body = """
            I am in danger. Please help me.

            My current location:
            Latitude: $lat
            Longitude: $lng

            Google Maps Link:
            $mapsLink
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(guardianEmail))
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
