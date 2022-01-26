package com.example.baaten

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private  lateinit var editEmail : EditText
    private lateinit var editPassword : EditText
    private lateinit var signInBtn : Button
    private lateinit var signUpBtn : Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        mAuth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        signInBtn = findViewById(R.id.signInBtn)
        signUpBtn = findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val intent = Intent(this,sign_up::class.java)
            startActivity(intent)
        }

        signInBtn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            login(email,password);
        }
    }

    private fun login(email: String, password: String){
        //login for login user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent= Intent(this@LogIn, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LogIn,"User doesn't exist",Toast.LENGTH_SHORT).show()
                }
            }
    }
}