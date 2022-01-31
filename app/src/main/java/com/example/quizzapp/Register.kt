package com.example.quizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.util.Log


class Register : AppCompatActivity() {


    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
        registerListener()

    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPassword2 = findViewById(R.id.editTextPassword2)
        buttonSubmit = findViewById(R.id.buttonRegister)

    }

    private fun registerListener() {

        buttonSubmit.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val password2 = editTextPassword2.text.toString()

            if (email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "FILL LINES!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != password2) {
                Toast.makeText(this, "Passwords must be same!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (!email.contains("@") or !email.contains(".")) {
                Toast.makeText(this, "Incorrect mail!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password.length < 9) {
                Toast.makeText(this, "Password must contain 9 symbols!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }else if (email.length<10) {
                Toast.makeText(this, "Incorrect mail!", Toast.LENGTH_SHORT).show()
            }



            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                    }else {
                        Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
                    }
                }



        }



    }





}
