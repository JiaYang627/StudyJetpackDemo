package cn.jiayang.studyjetpackdemo.base

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.jiayang.kotlinstudyjetpack.base.ActivityStackManager
import cn.jiayang.kotlinstudyjetpack.base.createViewBindingForAct
import cn.jiayang.studyjetpackdemo.utils.AdaptScreenUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author ：张 奎
 * @date ：2020-04-28 14：27
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), CoroutineScope by MainScope(){

    private var mBinding : VB?= null
    val mBindingView :VB get() = mBinding!!

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 使用的 ViewBinding 没有使用 DataBinding
        // 反射机制 虽说反射会影响性能  早期使用 ButterKnife  也会影响性能 不是依旧使用了 ？
        mBinding= createViewBindingForAct(layoutInflater)

        setContentView(mBinding!!.root)
        if (needTransparentStatus()) transparentStatusBar()
        initActivity(savedInstanceState)
        ActivityStackManager.addCurrentAct(this)
    }

    abstract fun initActivity(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()

        ActivityStackManager.removeActivity(this)
        cancel()
        mBinding = null
    }
    /** 透明状态栏 */
    open fun transparentStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()
    }
    protected open fun needTransparentStatus(): Boolean = false

    inline fun<reified T :ViewModel> setViewModel(block :() -> ViewModelProvider.NewInstanceFactory) :T{

         return ViewModelProvider(this, block()).get(T::class.java)

    }

    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(),750)
    }

}