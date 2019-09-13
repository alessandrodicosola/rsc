package it.poliba.adicosola1.rsclient.common.ui

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject

abstract class RSObjectAdapter<ItemType, ValueType, V : ViewDataBinding, VH : GenericViewHolder<V, RSObject<ItemType, ValueType>>>(
    factory: Factory<RSObject<ItemType, ValueType>, V, VH>
) :
    BaseAdapter<RSObject<ItemType, ValueType>, V, VH>(factory, RSObjectDiffUtil())

class RSObjectDiffUtil<ItemType, ValueType> : DiffUtil.ItemCallback<RSObject<ItemType, ValueType>>() {
    override fun areItemsTheSame(
        oldItem: RSObject<ItemType, ValueType>,
        newItem: RSObject<ItemType, ValueType>
    ): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: RSObject<ItemType, ValueType>,
        newItem: RSObject<ItemType, ValueType>
    ): Boolean {
        return oldItem == newItem
    }

}