package cn.jiayang.studyjetpackdemo.ui.find

import android.os.Bundle
import android.view.View
import cn.jiayang.studyjetpackdemo.base.BaseFragment
import cn.jiayang.studyjetpackdemo.databinding.FragmentFindBinding
import cn.jiayang.studyjetpackdemo.utils.LogUtils

/**
 * @author ：张 奎
 * @date ：2020-05-11 15：14
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class FindFragment : BaseFragment<FragmentFindBinding>() {
    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        LogUtils.error("FindFragment is init")
    }
}