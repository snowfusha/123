package com.example.quizzapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Reset : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        init()

        registerListeners()

    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail2)
        buttonSend = findViewById(R.id.buttonSend)
    }

    private fun registerListeners() {
        buttonSend.setOnClickListener {

            val email = editTextEmail.text.toString()

            if (email.isEmpty()) {
                editTextEmail.error = "FILL LINES"
                return@setOnClickListener
            }
            else if (!email.contains("@")) {
                editTextEmail.error = "INCORRECT MAIL"
                return@setOnClickListener
            }
            else if (!email.contains(".")) {
                editTextEmail.error = "INCORRECT MAIL"
                return@setOnClickListener
            }
            else if (email.length<8) {
                editTextEmail.error = "INCORRECT MAIL"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val buttonReset = findViewById<Button>(R.id.buttonSend)
                        buttonReset.setOnClickListener {
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        }

                    }
                }

        }
    }
}