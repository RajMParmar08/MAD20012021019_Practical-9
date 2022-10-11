package com.example.mad20012021019_practical_9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad20012021019_practical_9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        lsitview
//        id
//        accessid in main kt
//                acces object of that id in main.kt
//        read all sms and store in array list <smsview>
//        create obj of adapter of listview and assigned to listview object
//        manifest file permissions
//        requestSMspermission() after oncreate() in main.kt
//        cont...loadsmsinbox
//        issmsread/write
    }
}