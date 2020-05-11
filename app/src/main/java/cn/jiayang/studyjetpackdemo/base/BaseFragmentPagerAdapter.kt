package cn.jiayang.studyjetpackdemo.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * 用于 Fragment 创建 内部Fragment
 *
 * @author ：张 奎
 * @date ：2020-05-11 15：08
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class BaseFragmentPagerAdapter(
    fragment: FragmentManager,
    fragments: ArrayList<out Fragment>,
    titles: Array<String>? = null
) :
    FragmentPagerAdapter(fragment, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mFragments = fragments
    private var mTitles = titles

    init {
        if (mTitles.isNullOrEmpty())
            mTitles = Array(fragments.size) { "" }
    }

    override fun getItem(position: Int): Fragment = mFragments[position]
    override fun getCount(): Int = mFragments.size


    override fun getPageTitle(position: Int): CharSequence? =
        if (mTitles.isNullOrEmpty()) super.getPageTitle(position) else mTitles!![position]

}