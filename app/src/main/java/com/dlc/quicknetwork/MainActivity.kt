package com.dlc.quicknetwork

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dlc.quicknetwork.base.secarity.AESClientUtil
import com.dlc.quicknetwork.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private val userRequestViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text: TextView = findViewById(R.id.tv_a)
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["type"] = 1
        hashMap["passwd"] = AESClientUtil.Encrypt("123456")
        hashMap["loginId"] = AESClientUtil.Encrypt("15727877740")
        userRequestViewModel.getUserData(hashMap)

        //LiveData数据绑定
        userRequestViewModel.userData.observe(this, {
            text.text = it.nickname
        })

    }
}