package cn.jiayang.kotlinstudyjetpack.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author ：张 奎
 * @date ：2020-04-29 10：07
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
open class BaseViewHolder(val mBinding: ViewBinding) : RecyclerView.ViewHolder(mBinding.root) {
}