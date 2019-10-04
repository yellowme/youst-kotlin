package mx.yellowme.youst.core.hooks.recycler

import androidx.recyclerview.widget.RecyclerView

/**
 * Base class to implement a RecyclerView based list. In order to use this class
 * is necessary to implement a Custom ViewHolder over which the adapter is going to operate.
 *
 * @author luisburgos
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class SimpleRecyclerAdapter<Item, CustomViewHolder : RecyclerViewHolderDecorator<Item>> protected constructor(
    itemList: MutableList<Item>,
    val itemListener: ItemListener<Item>?
) : RecyclerView.Adapter<CustomViewHolder>() {

    protected lateinit var mItemList: MutableList<Item>

    init {
        setList(itemList)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        getItem(position)?.let {
            holder.decorate(it)
        }
    }

    /**
     * Replaces all the objects of the current list on adapter.
     *
     * @param items list of object to replaced the current list.
     */
    fun replaceData(items: MutableList<Item>) {
        setList(items)
        notifyDataSetChanged()
    }

    /**
     * Adds more objects to current list on adapter.
     *
     * @param items list of new objects to be added to the current list.
     */
    fun addData(items: MutableList<Item>) {
        addToList(items)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mItemList.size
    }

    fun getItem(position: Int): Item? {
        return mItemList[position]
    }

    /**
     * Helper method to interact with the item list variable.
     */
    protected fun setList(items: MutableList<Item>) {
        mItemList = items
    }

    /**
     * Helper method to interact with the item list variable.
     */
    protected fun addToList(items: List<Item>) {
        mItemList.addAll(items)
    }
}

interface ItemListener<Item> {
    fun onItemClick(item: Item?)
}
