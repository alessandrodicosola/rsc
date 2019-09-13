package it.poliba.adicosola1.rsclient.ui.presenter

import it.poliba.adicosola1.rsclient.BR
import it.poliba.adicosola1.rsclient.R
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.ui.BaseAdapter
import it.poliba.adicosola1.rsclient.common.ui.RSObjectAdapter
import it.poliba.adicosola1.rsclient.common.ui.RSObjectViewHolder
import it.poliba.adicosola1.rsclient.databinding.ViewholderGameBinding

class GameAdapter(factory: Factory<RSObject<Int, Double>, ViewholderGameBinding, RSObjectViewHolder<Int, Double, ViewholderGameBinding>>) :

    RSObjectAdapter<Int, Double, ViewholderGameBinding, RSObjectViewHolder<Int, Double, ViewholderGameBinding>>(factory) {

    override fun getLayoutId(): Int {
        return R.layout.viewholder_game
    }

}

class GameViewHolder(binding: ViewholderGameBinding) : RSObjectViewHolder<Int, Double, ViewholderGameBinding>(binding) {

    override fun getVariableId(): Int {
        return BR.game
    }

}

class GameAdapterFactory :
    BaseAdapter.Factory<RSObject<Int, Double>, ViewholderGameBinding, RSObjectViewHolder<Int, Double, ViewholderGameBinding>> {
    override fun create(
        binding: ViewholderGameBinding,
        viewType: Int
    ): RSObjectViewHolder<Int, Double, ViewholderGameBinding> {
        return GameViewHolder(binding)
    }


}