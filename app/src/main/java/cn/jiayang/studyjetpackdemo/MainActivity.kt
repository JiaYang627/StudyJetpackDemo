package cn.jiayang.studyjetpackdemo

import android.os.Bundle
import cn.jiayang.studyjetpackdemo.base.BaseActivity
import cn.jiayang.studyjetpackdemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initActivity(savedInstanceState: Bundle?) {
    }

    override fun needTransparentStatus(): Boolean = true
}
