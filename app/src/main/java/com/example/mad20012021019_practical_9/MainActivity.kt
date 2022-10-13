package com.example.mad20012021019_practical_9
//        listview
//        id
//        accessid in main kt
//                acces object of that id in main.kt
//        read all sms and store in array list <smsview>
//        create obj of adapter of listview and assigned to listview object
//        manifest file permissions
//        requestSMspermission() after oncreate() in main.kt
//        cont...loadsmsinbox
//        issmsread/write
import android.Manifest
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mad20012021019_practical_9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lv:ListView
    private lateinit var al:ArrayList<SMSView>
    private lateinit var smsreceive:BroadcastReceiver
    private val SMS_PERMISSION_CODE=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lv=binding.listview
        al=ArrayList()
        if(checkRequestPermission()){
            loadSMSInbox()
        }
        smsreceive=SMSBroadCastReciever()
        registerReceiver(smsreceive, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
        smsreceive.listener =ListnerImplement()

    }
    inner class ListnerImplement:SMSBroadCastReciever.Listener
    {
        override fun onTextRecieved(sPhone: String?, sMsg: String?) {

        }
    }
    private fun requestSMSPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_SMS,)){

        }
        ActivityCompat.requestPermissions((this, arrayOf(Manifest.permission.READ_SMS,
        Manifest.permission.SEND_SMS,
        Manifest.permission.RECEIVE_SMS
        ),SMS_PERMISSION_CODE)
    }
    private val isSMSReadPermission:Boolean
    get()=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)==PackageManager.PERMISSION_GRANTED
    private val isSMSWritePermission:Boolean
    get()=ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED
    private fun checkRequestPermission(): Boolean {
        return if (!isSMSReadPermission || !isSMSWritePermission) {
            requestSMSPermission()
            false
        } else true
    }

    private fun loadSMSInbox() {
        if (!checkRequestPermission()) return
        val uriSMS = Uri.parse("content://sms/inbox")
        val c = contentResolver.query(uriSMS, null, null, null, null)
        al.clear()
        while (c!!.moveToNext()) {
            al.add(SMSView(c.getString(2),c.getString(12)))
        }
        lv.adapter = SMSViewAdapter(this,al)
    }

    override fun onDestroy() {
        unregisterReceiver(smsreceive)
        super.onDestroy()
    }}