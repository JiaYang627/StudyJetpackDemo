package cn.jiayang.studyjetpackdemo

import android.content.Intent
import android.os.Bundle
import cn.jiayang.studyjetpackdemo.base.BaseActivity
import cn.jiayang.kotlinstudyjetpack.base.delayLaunch
import cn.jiayang.studyjetpackdemo.databinding.ActivitySplashBinding

/**
 * @author ：张 奎
 * @date ：2020-05-11 13：12
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun initActivity(savedInstanceState: Bundle?) {
        delayLaunch(2000){
            block = {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
        }
    }
}