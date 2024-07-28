package com.Exsample.epicmart.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.Exsample.epicmart.R
import com.Exsample.epicmart.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginpagelink.setOnClickListener{
            val intent = Intent (this ,loginActivity ::class.java)
            startActivity(intent)
        }

        binding.afterregistrationbutten.setOnClickListener {
            val email = binding.editTextText13.text.toString()
            val pass = binding.editTextText15.text.toString()
            val cofirmpass = binding.editTextTextPassword.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty() && cofirmpass.isNotEmpty()) {
                if (pass == cofirmpass) {

                    firebaseAuth.createUserWithEmailAndPassword(email , pass ).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent (this ,loginActivity ::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }


                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Empty Fields are not Allowed !", Toast.LENGTH_SHORT).show()
            }


            //   ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            //       val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            //       v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            //       insets


            //   }


            //   val textView = findViewById<TextView>(R.id.loginpagelink)
            //     textView.setOnClickListener {
            //       val intent = Intent(this, loginActivity::class.java)
            //        startActivity(intent)
            //     }

            //     var startbutton = findViewById<Button>(R.id.afterregistrationbutten)
            //   startbutton.setOnClickListener {
            //       val Intent = Intent(this, loginActivity::class.java)
            //        startActivity(Intent)
            //   }


        }
    }
}