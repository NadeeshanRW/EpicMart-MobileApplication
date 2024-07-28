package com.Exsample.epicmart.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.Exsample.epicmart.R
import com.Exsample.epicmart.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {

private  lateinit var binding: ActivityLoginBinding
private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Registerlink.setOnClickListener{
            val intent = Intent (this , signupActivity::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener{
            val email = binding.editTextText.text.toString()
            val pass = binding.editTextTextPassword.text.toString()



            if (email.isNotEmpty() && pass.isNotEmpty() ) {


                    firebaseAuth.signInWithEmailAndPassword(email , pass ).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent (this , HomeActivity ::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }




            } else {
                Toast.makeText(this, "Empty Fields are not Allowed !", Toast.LENGTH_SHORT).show()
            }


        }














      //  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))

      //  { v, insets ->
       //     val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
     //       v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
       //     insets
    //    }

     //   val textView = findViewById<TextView>(R.id.Registerlink)
      //  textView.setOnClickListener {
    //        val intent = Intent(this, signupActivity::class.java)
  //          startActivity(intent)


   //     }
    }
}