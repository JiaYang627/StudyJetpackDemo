package cn.jiayang.studyjetpackdemo.base

import android.view.View
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.jiayang.kotlinstudyjetpack.base.BaseViewHolder


/**
 * @author ：张 奎
 * @date ：2020-05-25 16：40
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
class HeaderAndFooterWrapper(private val mAdapter: BaseRvAdapter<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val BASE_ITEM_TYPE_HEADER = 100000
    private val BASE_ITEM_TYPE_FOOTER = 200000
    private val mHeaderViews: SparseArrayCompat<View> = SparseArrayCompat<View>()
    private val mFooterViews: SparseArrayCompat<View> = SparseArrayCompat<View>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (mHeaderViews.get(viewType) != null) {
            return MyViewHolder(mHeaderViews.get(viewType));
        } else if (mFooterViews.get(viewType) != null) {
            return MyViewHolder(mFooterViews.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isHeaderViewPos(position))
            return;
        if (isFooterViewPos(position))
            return;
        mAdapter.onBindViewHolder(holder as BaseViewHolder, position - getHeadersCount());
    }

    override fun getItemCount(): Int {
        return getHeadersCount() + getFootersCount() + getRealItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position)
        } else if (isFooterViewPos(position)) {
            return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount())
        }
        return mAdapter.getItemViewType(position - getHeadersCount())
    }

    /**
     * 获取正常数据的size
     * @return
     */
    private fun getRealItemCount(): Int {
        return mAdapter.itemCount
    }

    /**
     * 判断是否是Header
     * @param position
     * @return
     */
    private fun isHeaderViewPos(position: Int): Boolean {
        return position < getHeadersCount()
    }

    /**
     * 判断是否是Footer
     * @param position
     * @return
     */
    private fun isFooterViewPos(position: Int): Boolean {
        return position >= getHeadersCount() + getRealItemCount()
    }


    fun addHeaderView(view: View?) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view)
    }

    fun addFooterView(view: View?) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view)
    }

    private fun getHeadersCount(): Int {
        return mHeaderViews.size()
    }

    private fun getFootersCount(): Int {
        return mFooterViews.size()
    }

    /**
     * 适配网格布局
     * @param recyclerView
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (isHeaderViewPos(position) || isFooterViewPos(position)) layoutManager.spanCount else 1
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder is BaseViewHolder) {
            mAdapter.onViewAttachedToWindow(holder)
        }
        val position = holder.layoutPosition
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
                lp.isFullSpan = true
            }
        }
    }

    internal class MyViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!)
}