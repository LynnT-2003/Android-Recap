package com.example.androidrecap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidrecap.databinding.ActivityFirebaseBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.gson.Gson
import java.time.LocalDateTime

class FirebaseActivity : AppCompatActivity() {
    private val view: ActivityFirebaseBinding by lazy {
        ActivityFirebaseBinding.inflate(
            layoutInflater
        )
    }

    data class FirebaseStudent(
        val studentId: Int? = null,
        val name: String? = null,
        val grade: Int? = null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        val database = Firebase.database(
            "https://androidrecap-default-rtdb.asia-southeast1.firebasedatabase.app/"
        )

        val ref = database.getReference("message")
        val studentRef = database.getReference("students").child("1")

        studentRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val student = snapshot.getValue(FirebaseStudent::class.java)
                Toast.makeText(
                    this@FirebaseActivity,
                    "Student: ${student?.name}, Grade: ${student?.grade}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        ref.setValue("Current time is ${LocalDateTime.now()}")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val message = snapshot.value.toString()
                Toast.makeText(this@FirebaseActivity, message, Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}