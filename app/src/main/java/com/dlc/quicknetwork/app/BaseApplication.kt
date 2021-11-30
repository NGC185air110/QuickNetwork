package com.dlc.quicknetwork.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Handler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.properties.Delegates
import kotlin.system.exitProcess

/**
 * title：
 * description：
 * author：dinglicheng on 2021/6/11 10:39
 */
class BaseApplication : Application() {

    companion object {
        var instance: BaseApplication? = null
        var context: Context by Delegates.notNull()
        private var mHandler: Handler? = null//主线程Handler

        //运用list来保存们每一个activity是关键
        private val mList = LinkedList<Activity>()

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instance = this
    }


    fun addActivity(activity: Activity) {
        mList.add(activity)
    }

    fun getActivityList(): LinkedList<Activity> {
        return mList
    }

    //杀掉所有activity
    fun exitAllActivity() {
        GlobalScope.launch {
            try {
                for (activity in mList) {
                    activity.finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                exitProcess(0)
            }
        }
    }
}