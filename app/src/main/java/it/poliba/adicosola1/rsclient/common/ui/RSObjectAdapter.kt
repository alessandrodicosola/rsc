package it.poliba.adicosola1.rsclient.common.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject

abstract class RSObjectAdapter<V : ViewDataBinding, VH : GenericViewHolder<V, RSObject>>(factory: Factory<RSObject, V, VH>) :
    BaseAdapter<RSObject, V, VH>(factory, RSObjectDiffUtil())

class RSObjectDiffUtil : DiffUtil.ItemCallback<RSObject>() {
    override fun areItemsTheSame(oldItem: RSObject, newItem: RSObject): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RSObject, newItem: RSObject): Boolean {
        return oldItem.id == newItem.id && oldItem.score == newItem.score
    }

}