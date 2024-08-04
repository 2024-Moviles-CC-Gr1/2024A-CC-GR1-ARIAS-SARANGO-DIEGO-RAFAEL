package com.example.a04_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class ListeningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)

        val closeButton = findViewById<ImageButton>(R.id.btn_close)
        closeButton.setOnClickListener {
            finish()
        }
    }
}