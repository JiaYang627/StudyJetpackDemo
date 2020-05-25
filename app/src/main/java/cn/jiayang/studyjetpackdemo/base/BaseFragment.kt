package cn.jiayang.studyjetpackdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import cn.jiayang.kotlinstudyjetpack.base.createViewBindingForFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author ：张 奎
 * @date ：2020-04-28 17：20
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment(), CoroutineScope by MainScope() {

    private var mBinding: VB? = null
    protected lateinit var mNavHostController :NavController

    val mBindingView :VB get() = mBinding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mNavHostController = NavHostFragment.findNavController(this)

        mBinding = createViewBindingForFragment(layoutInflater,container)
        return if (mBinding != null) {
            mBinding!!.root.apply { (parent as? ViewGroup)?.removeView(this) }
        } else super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment(view, savedInstanceState)
    }


    abstract fun initFragment(view: View, savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        mBinding = null
    }

    inline fun<reified T : ViewModel> setViewModel(block :() -> ViewModelProvider.NewInstanceFactory) :T{

        return ViewModelProvider(requireActivity(), block()).get(T::class.java)

    }
}