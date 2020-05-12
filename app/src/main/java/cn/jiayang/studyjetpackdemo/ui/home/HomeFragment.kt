package cn.jiayang.studyjetpackdemo.ui.home

import android.os.Bundle
import android.view.View
import cn.jiayang.studyjetpackdemo.base.BaseFragment
import cn.jiayang.studyjetpackdemo.databinding.FragmentHomeBinding
import cn.jiayang.studyjetpackdemo.utils.LogUtils

/**
 * @author ：张 奎
 * @date ：2020-05-11 15：11
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        LogUtils.error("HomeFragment is init")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.error("HomeFragment is onResume")
    }

}