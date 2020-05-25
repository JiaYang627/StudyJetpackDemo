package cn.jiayang.studyjetpackdemo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author ：张 奎
 * @date ：2020-05-22 14：09
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class HomeViewModelFactory(private val repository:HomeRepository) :ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T

    }
}