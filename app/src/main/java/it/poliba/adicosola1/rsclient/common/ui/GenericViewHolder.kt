package it.poliba.adicosola1.rsclient.common.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Generic view holder
 * Use [RSObjectViewHolder]
 */
abstract class GenericViewHolder<V : ViewDataBinding, T>(private val binding: V) :
    RecyclerView.ViewHolder(binding.root) {
    /**
    @return Returns the data's id used inside ViewHolder layout file
     */
    abstract fun getVariableId(): Int

    fun bind(obj: T) {
        binding.setVariable(getVariableId(), obj)
        binding.executePendingBindings()
    }
}