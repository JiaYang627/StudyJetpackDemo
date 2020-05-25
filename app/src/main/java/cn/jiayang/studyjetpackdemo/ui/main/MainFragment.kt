package cn.jiayang.studyjetpackdemo.ui.main

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi
import cn.jiayang.studyjetpackdemo.R
import cn.jiayang.studyjetpackdemo.base.BaseFragment
import cn.jiayang.studyjetpackdemo.base.BaseFragmentPagerAdapter
import cn.jiayang.studyjetpackdemo.databinding.FragmentMainBinding
import cn.jiayang.studyjetpackdemo.ui.find.FindFragment
import cn.jiayang.studyjetpackdemo.ui.home.HomeFragment
import cn.jiayang.studyjetpackdemo.ui.mine.MineFragment
import cn.jiayang.studyjetpackdemo.ui.sofa.SofaFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.LabelVisibilityMode


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
    private val mMainViewModel :MainViewModel by lazy {
        setViewModel<MainViewModel> {
            MainViewModelFactory(MainRepository())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        initViewPager()
        initBottomNavigationView()

    }

    private fun initViewPager() {
        mBindingView.mViewPager.apply {
            adapter = mViewPagerAdapter
            offscreenPageLimit = mViewPagerAdapter.count
            setCurrentItem(0, false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initBottomNavigationView() {


        setBottomIcon()

        mBindingView.mBottomNavigation.apply {


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
                        setCurrentItemViewPager(mClickPosition)

                    }
                    R.id.navigation_mine -> {
                        mClickPosition = 3
                        setCurrentItemViewPager(mClickPosition)
                    }
                    R.id.navigation_add -> {
                        mNavHostController.navigate(R.id.action_mainFragment_to_addFragment)
                        false
                    }
                    else -> {
                        false
                    }
                }
            }
        }

    }

    private fun setCurrentItemViewPager(item: Int = 0, mSmoothScroll: Boolean? = false): Boolean {
        return if (mCurrentPosition != item) {
            mBindingView.mViewPager.setCurrentItem(item, false)
            mCurrentPosition = item
            true
        } else {
            false
        }
    }

    /**
     * 如果想要禁止掉所有按钮的点击浮动效果。
     * 那么还需要给选中和未选中的按钮配置一样大小的字号。
     *
     *  在MainActivity布局的AppBottomBar标签增加如下配置，
     *  @style/active，@style/inActive 在style.xml中
     *  app:itemTextAppearanceActive="@style/active"
     *  app:itemTextAppearanceInactive="@style/inActive"
     */
    // 此处改变 中间图片Icon 大小， 及默认渲染颜色
    @SuppressLint("RestrictedApi", "WrongConstant")
    private fun setBottomIcon() {

        val menuView = mBindingView.mBottomNavigation.getChildAt(0) as BottomNavigationMenuView

        /**
         * /**
         * Label behaves as "labeled" when there are 3 items or less, or "selected" when there are 4 items
         * or more.
        */
        int LABEL_VISIBILITY_AUTO = -1;

        /** Label is shown on the selected navigation item. */
        int LABEL_VISIBILITY_SELECTED = 0;

        /** Label is shown on all navigation items. */
        int LABEL_VISIBILITY_LABELED = 1;

        /** Label is not shown on any navigation items. */
        int LABEL_VISIBILITY_UNLABELED = 2;
         */

        // 此处只是设置 item 大于3个 文字不显示情况
        menuView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        menuView.updateMenuView()


        val childAt = menuView.getChildAt(2) as BottomNavigationItemView

        childAt.setIconTintList(ColorStateList.valueOf(Color.parseColor("#ff678f")))
        //禁止掉点按时 上下浮动的效果
        childAt.setShifting(false)

        val iconView: View =
            childAt.findViewById(R.id.icon)
        val layoutParams = iconView.layoutParams
        val displayMetrics = resources.displayMetrics
        layoutParams.height =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45f, displayMetrics).toInt()
        layoutParams.width =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45f, displayMetrics).toInt()
        iconView.layoutParams = layoutParams

    }
}