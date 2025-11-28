package com.example.womansaftey

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PoliceContactsActivity : AppCompatActivity() {

    private lateinit var btnCallPolice: Button
    private lateinit var btnOpenNearbyPolice: Button

    private val callPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                callPolice()
            } else {
                Toast.makeText(this, "Call permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_police_contacts)

        btnCallPolice = findViewById(R.id.btnCallPolice)
        btnOpenNearbyPolice = findViewById(R.id.btnOpenNearbyPolice)

        btnCallPolice.setOnClickListener {
            checkCallPermissionAndProceed()
        }

        btnOpenNearbyPolice.setOnClickListener {
            openNearbyPoliceInMap()
        }
    }

    private fun checkCallPermissionAndProceed() {
        val permission = Manifest.permission.CALL_PHONE
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                callPolice()
            }
            shouldShowRequestPermissionRationale(permission) -> {
                Toast.makeText(this, "Call permission is needed to call police.", Toast.LENGTH_LONG).show()
                callPermissionLauncher.launch(permission)
            }
            else -> {
                callPermissionLauncher.launch(permission)
            }
        }
    }

    private fun callPolice() {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:112")  // Emergency number
        }
        startActivity(intent)
    }

    private fun openNearbyPoliceInMap() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=police+station")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, gmmIntentUri))
        }
    }
}
