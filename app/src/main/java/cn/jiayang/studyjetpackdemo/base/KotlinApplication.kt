package cn.jiayang.studyjetpackdemo.base

import android.app.Application
import android.content.Context

/**
 * @author ：张 奎
 * @date ：2020-05-11 10：31
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class KotlinApplication :Application(){


    override fun onCreate() {
        super.onCreate()
        mApplication = applicationContext
    }
    companion object{

        lateinit var mApplication: Context
    }
}