package com.example.gouvernoratsrecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailsGover : AppCompatActivity() {
    lateinit var nameDet:TextView
    lateinit var imagedet:ImageView
    lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_gover)
        button=findViewById(R.id.button)
        var photo=intent.getIntExtra("governorate_photo",R.drawable.appstore)
        var name=intent.getStringExtra("governorate_name")

        nameDet=findViewById(R.id.nameDetails)
        imagedet=findViewById(R.id.photoDetails)
        nameDet.text=name;

        imagedet.setImageResource(photo);

        button.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java);
            startActivity(intent)
        }

        
    }
}


