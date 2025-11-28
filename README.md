# 📱 Women Safety & Security App – Android (Kotlin)

The Women Safety App is an emergency safety application designed to help women seek immediate assistance during unsafe situations. The app allows users to **share their live GPS location via email**, **connect with police**, and **register harassment complaints instantly** with a simple and user-friendly interface.

---

## 🚀 Features

| Module | Description |
|--------|-------------|
| 📍 Live Location Sharing | Sends current latitude & longitude to a guardian via email with Google Maps link. |
| 🚔 Police Assistance | Direct call to Women Safety Helpline (112) and open nearby police stations in Google Maps. |
| 📝 Complaint Registration | Users can enter incident details and submit via email to concerned authorities. |
| 🧭 Simple UI | Designed for emergency situations with quick access buttons. |

---

## 🛠 Tech Stack

| Technology | Details |
|------------|---------|
| Language | Kotlin |
| UI | XML (Android ConstraintLayout) |
| Location API | Google Play Services – Fused Location Provider |
| Minimum SDK | 21 (Android 5.0) |
| IDE | Android Studio (Gradle) |

---

## 📂 Folder Structure



app/src/main/java/com.example.womansafety/
├── MainActivity.kt
├── ShareLocationActivity.kt
├── PoliceContactsActivity.kt
├── RegisterComplaintActivity.kt

app/src/main/res/layout/
├── activity_main.xml
├── activity_share_location.xml
├── activity_police_contacts.xml
└── activity_register_complaint.xml

AndroidManifest.xml
build.gradle.kts




---

## 🔧 Permissions Used

| Permission | Purpose |
|-----------|---------|
| ACCESS_FINE_LOCATION | Get GPS location |
| ACCESS_COARSE_LOCATION | Network-based location |
| INTERNET | Email & location services |
| CALL_PHONE | Call Emergency Helpline |

---

## 📌 How to Run the Project

1. Clone the repository  
   ```bash
   git clone https://github.com/yourusername/woman_safety.git


2. Open in Android Studio

3. Enable Google Play Services (required for location)

4. Run the app on:

Real device (recommended) ✔

Emulator with Play Store ✔





🌟 Future Enhancements (Planned)

🔴 SOS button to alert multiple contacts at once

📡 Emergency SMS when internet is OFF

🔊 Panic alarm alert sound

🔑 Guardian login system

🔐 Cloud database for complaints

👨‍💻 Developer

Shivam Kumar
B.Tech CSE | Android Development | Kotlin
📍 India
💼 Passionate about building mobile apps that solve real-world problems.

⭐ Support the Project

If you like this project, consider giving it a ⭐ on GitHub — it motivates contributors and showcases your work to recruiters!

