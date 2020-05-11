
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import cn.jiayang.kotlinstudyjetpack.base.BaseViewHolder
import cn.jiayang.studyjetpackdemo.base.OnItemClickListener
import cn.jiayang.studyjetpackdemo.base.OnItemLongClickListener

/**
 * @author ：张 奎
 * @date ：2020-04-29 10：04
 * 邮箱   ：JiaYang627@163.com / 272629247@qq.com
 */
abstract class BaseRvAdapter<T>(var mData : MutableList<T>?):
    RecyclerView.Adapter<BaseViewHolder>(){


    private var itemListener: OnItemClickListener? = null
    private var itemLongListener: OnItemLongClickListener? = null

    fun setOnItemListener(listener: OnItemClickListener?) {
        this.itemListener = listener
    }

    fun setOnItemLongListener(listener: OnItemLongClickListener?) {
        this.itemLongListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return BaseViewHolder(setViewHolder(parent,viewType))
    }

    override fun getItemCount(): Int = mData?.size?:0

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val data =getItemData(position) ?: return
        setVariable(data, position, holder)
        holder.mBinding.root.setOnClickListener { v -> itemListener?.onItemClick(position, v) }
        holder.mBinding.root.setOnLongClickListener { v ->
            itemLongListener?.onItemLongClick(position, v)
            false
        }
    }

    abstract fun setViewHolder(parent: ViewGroup, viewType: Int) :ViewBinding
    /**
     * @param data 列表中当前 position 的数据
     * @param position 数据的位置
     * @param holder
     */
    abstract fun setVariable(data: T, position: Int, holder: BaseViewHolder)
    /**
     * 获取对应 position 下的数据
     * @param position
     */
    fun getItemData(position: Int): T? = mData!![position]

    fun setNewDataAll(mList : MutableList<T>){
        mData?.let {
            it.clear()
            it.addAll(mList)
        }
        notifyDataSetChanged()
    }

    fun addData(data :T){
        mData?.add(data)
        notifyDataSetChanged()

    }
    fun addDataAll(mList: MutableList<T>) {
        mData?.addAll(mList)
        notifyDataSetChanged()

    }

    fun clearAll() {
        mData?.clear()
        notifyDataSetChanged()

    }
}