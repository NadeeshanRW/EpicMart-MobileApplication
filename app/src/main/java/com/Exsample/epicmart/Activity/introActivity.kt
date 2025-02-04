package com.Exsample.epicmart.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.Exsample.epicmart.R

class introActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var startbutton = findViewById<Button>(R.id.StartButton)
        startbutton.setOnClickListener {
            val Intent = Intent(this, signupActivity::class.java)
            startActivity(Intent)
        }

        val textView = findViewById<TextView>(R.id.introloginlink)
        textView.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)


        }
    }
}