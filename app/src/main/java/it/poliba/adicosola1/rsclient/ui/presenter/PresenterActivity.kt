package it.poliba.adicosola1.rsclient.ui.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import it.poliba.adicosola1.rsclient.R
import it.poliba.adicosola1.rsclient.databinding.ActivityPresenterBinding
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel


class PresenterActivity : AppCompatActivity() {

    private val viewmodel by currentScope.viewModel<PresenterViewModel>(this)


    lateinit var layout: ActivityPresenterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")


        layout = DataBindingUtil.setContentView(this, R.layout.activity_presenter)

        val recyclerViewLayout = GridLayoutManager(this, 2)

        val factory = GameAdapterFactory()
        val gameAdapter = GameAdapter(factory)

        viewmodel.items.observe(this, Observer { gameAdapter.updateList(it) })
        viewmodel.status.observe(
            this,
            Observer { Snackbar.make(layout.coordinatorLayout, it.get()?.message!!, Snackbar.LENGTH_LONG).show() })

        layout.ownedGamesRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = recyclerViewLayout
            adapter = gameAdapter
        }

    }

}