package com.example.readsms

import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.readsms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSms()
    }


//     Using Content Provider
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getSms(){
        val uri = Uri.parse("content://sms//inbox")
        val projection = arrayOf(SMS_SENDER, SMS_BODY)

        val cursor:Cursor = contentResolver.query(uri,projection,null,null)!!

        while (cursor.moveToNext()){
            for (i in 0 until cursor.columnCount){
                Log.d(LOG_TAG,"$i - ${cursor.getColumnName(i)} - ${cursor.getString(i)}")
            }
        }
    }


    companion object{

        private const val LOG_TAG = "MAIN_ACTIVITY"
        private const val SMS_BODY = "BODY"
        private const val SMS_SENDER = "ADDRESS"

    }

}