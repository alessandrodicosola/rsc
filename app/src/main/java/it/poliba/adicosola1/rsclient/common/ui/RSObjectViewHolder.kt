package it.poliba.adicosola1.rsclient.common.ui

import androidx.databinding.ViewDataBinding
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject

abstract class RSObjectViewHolder<ItemType, ValueType, V : ViewDataBinding>(binding: V) :
    GenericViewHolder<V, RSObject<ItemType, ValueType>>(binding)