package cn.jiayang.studyjetpackdemo.base

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * @author ：张 奎
 * @date ：2020-05-11 16：01
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class ScrollViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    private var mNoScroll: Boolean? = true


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (mNoScroll!!) {

            false
        } else {

            super.onTouchEvent(ev)
        }

    }

    fun setNoScroll(mNoScroll: Boolean? = true) {
        this.mNoScroll = mNoScroll
    }
}