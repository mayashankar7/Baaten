package com.example.baaten

import android.R.attr
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class sign_up : AppCompatActivity() {

    private lateinit var editName :EditText
    private  lateinit var editEmail : EditText
    private lateinit var editPassword : EditText
    private lateinit var signUPBtn : Button

    private lateinit var mAuth : FirebaseAuth
    private lateinit var signInbtn2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        signUPBtn = findViewById(R.id.signUpBtn)

        signInbtn2 = findViewById(R.id.SignInBtn2)

        mAuth = FirebaseAuth.getInstance()

        signUPBtn.setOnClickListener {
            val email = editEmail.text.toString()
            val password= editPassword.text.toString()

            signUp(email, password)
        }

        signInbtn2.setOnClickListener {
            var intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }
     private fun signUp(email: String, password: String){
         //logic to register new user
         mAuth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this) { task ->
                 if (task.isSuccessful) {
                     //code for jumping to home
                     val intent= Intent(this@sign_up, MainActivity::class.java)
                     startActivity(intent)
                 } else {
                     Toast.makeText(this@sign_up,"Some Error Occured",Toast.LENGTH_SHORT).show()
                 }
             }
     }
}