package cn.jiayang.studyjetpackdemo.ui.main

import android.os.Bundle
import android.view.View
import cn.jiayang.studyjetpackdemo.R
import cn.jiayang.studyjetpackdemo.base.BaseFragment
import cn.jiayang.studyjetpackdemo.base.BaseFragmentPagerAdapter
import cn.jiayang.studyjetpackdemo.databinding.FragmentMainBinding
import cn.jiayang.studyjetpackdemo.ui.find.FindFragment
import cn.jiayang.studyjetpackdemo.ui.home.HomeFragment
import cn.jiayang.studyjetpackdemo.ui.mine.MineFragment
import cn.jiayang.studyjetpackdemo.ui.sofa.SofaFragment
import cn.jiayang.studyjetpackdemo.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author ：张 奎
 * @date ：2020-05-11 13：46
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {


    private var mCurrentPosition = 0
    private val mViewPagerAdapter: BaseFragmentPagerAdapter by lazy {
        BaseFragmentPagerAdapter(
            childFragmentManager, arrayListOf(
                HomeFragment(),
                SofaFragment(),
                FindFragment(),
                MineFragment()

            )
        )
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        initViewPager()
        initBottomNavigationView()
        mBottomNavigation.setupWithViewPager(mBindingView.mViewPager)

    }

    private fun initViewPager() {
        mBindingView.mViewPager.apply {
            adapter = mViewPagerAdapter
            offscreenPageLimit = mViewPagerAdapter.count
            LogUtils.error("mViewPagerAdapter.count  -- > $offscreenPageLimit")
            setCurrentItem(0, false)
        }
    }

    private fun initBottomNavigationView() {
        mBindingView.mBottomNavigation.apply {

            enableAnimation(false)
            enableShiftingMode(false)
            enableItemShiftingMode(false)

//            setIconSizeAt(2, 45f, 45f)
//            setIconTintList(
//                2,
//                resources.getColorStateList(R.color.bottom_navigation_item_add_icon_color)
//            )



            setOnNavigationItemSelectedListener {

                var mClickPosition = 0
                when (it.itemId) {
                    R.id.navigation_home -> {
                        mClickPosition = 0
                        setCurrentItemViewPager(mClickPosition)
                    }
                    R.id.navigation_sofa -> {
                        mClickPosition = 1
                        setCurrentItemViewPager(mClickPosition)
                    }
                    R.id.navigation_find -> {
                        mClickPosition = 2
                        mBindingView.mViewPager.setCurrentItem(2, false)
                        setCurrentItemViewPager(mClickPosition)

                    }
                    R.id.navigation_mine -> {
                        mClickPosition = 3
                        setCurrentItemViewPager(mClickPosition)
                    }
                    else -> {
                        false
                    }
                }
            }
        }

    }

    private fun setCurrentItemViewPager(item: Int = 0, mSmoothScroll: Boolean? = false): Boolean {
        if (mCurrentPosition != item) {
//            mBindingView.mViewPager.setCurrentItem(item, false)
            mCurrentPosition = item
            return true
        } else {

            return false
        }
    }
}