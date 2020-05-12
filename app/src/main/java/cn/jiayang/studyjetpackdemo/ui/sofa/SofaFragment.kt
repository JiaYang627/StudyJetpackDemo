package cn.jiayang.studyjetpackdemo.ui.sofa

import android.os.Bundle
import android.view.View
import cn.jiayang.studyjetpackdemo.base.BaseFragment
import cn.jiayang.studyjetpackdemo.databinding.FragmentSofaBinding
import cn.jiayang.studyjetpackdemo.utils.LogUtils

/**
 * @author ：张 奎
 * @date ：2020-05-11 15：13
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class SofaFragment : BaseFragment<FragmentSofaBinding>() {
    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        LogUtils.error("SofaFragment is init")

    }

    override fun onResume() {
        super.onResume()
        LogUtils.error("SofaFragment is onResume")
    }
}