package com.example.quizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPassword2: EditText
    private lateinit var buttonRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        init2()
    }

    private fun init2() {
        buttonRegister.setOnClickListener {
            val register = editTextEmail.text.toString()
            val password1 = editTextPassword.text.toString()
            val password2 = editTextPassword2.text.toString()
            if (register.isEmpty() or password1.isEmpty() or password2.isEmpty()) {
                Toast.makeText(this, "საჭიროა სარეგისტრაციო ველების შევსება", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener

            } else if (!register.contains("@")) {
                Toast.makeText(this, "იმეილისთვის საჭიროა ძაღლუკის სიმბოლო - @", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener


            } else if (!register.contains(".")) {
                Toast.makeText(this, "იმეილისთვის საჭიროა წერტილი", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (password1.length <= 8) {

                Toast.makeText(this, "პაროლი უნდა შეიცავდეს მინიმუმ 9 სიმბოლოს", Toast.LENGTH_SHORT)
                    .show()

                return@setOnClickListener
            } else if (!password1.contains("1") && !password1.contains("2") && !password1.contains(
                    "3"
                ) &&
                !password1.contains("4") && !password1.contains("5") && !password1.contains(
                    "6"
                ) &&
                !password1.contains("7") && !password1.contains("8") && !password1.contains(
                    "9"
                ) &&
                !password1.contains("0")
            ) {

                Toast.makeText(this, "პაროლს უნდა ჰქონდეს ციფრები", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            } else if (password1 != password2) {


                Toast.makeText(this, "პაროლები უნდა იყოს ერთნაირი", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            } else {
                Toast.makeText(this,"ვსიო ჩემო ძმაო ან დაო , ან ბიძაშვილო,ან კაციტა",Toast.LENGTH_LONG).show()
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(register, password1)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "თქვენ წარმატებით დარეგისტრირდით!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }



            }

        }



    }

    private fun init() {

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPassword2 = findViewById(R.id.editTextPassword2)
        buttonRegister = findViewById(R.id.buttonRegister)

    }
}

