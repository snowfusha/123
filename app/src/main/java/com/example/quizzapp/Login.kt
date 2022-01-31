package com.example.quizzapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    private lateinit var buttonReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        loginListeners()


    }

    private fun init() {

        editTextEmail = findViewById(R.id.editTextEmail1)
        editTextPassword = findViewById(R.id.editTextPassword1)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.Register)
        buttonReset = findViewById(R.id.resetPassword)

    }

    private fun loginListeners() {

        buttonLogin.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty()) {
                editTextEmail.error = "FILL LINES"
                return@setOnClickListener
            }
            else if (email.length<8 || password.length<8){
                editTextEmail.error = "Incorrect mail"
                editTextPassword.error = "Password must contain 8 sybols"
                return@setOnClickListener
            }
            else if (!email.contains("@") || !email.contains(".")){
                editTextEmail.error = "Incorrect Mail"
            }
            else if(password.isEmpty()){
                editTextPassword.error = "Input password!"
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else {
                        Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
                    }
                }

        }

        buttonRegister.setOnClickListener() {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        buttonReset.setOnClickListener() {
            val intent = Intent(this, Reset::class.java)
            startActivity(intent)
        }

    }




}