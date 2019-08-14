package it.poliba.adicosola1.rsclient.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import it.poliba.adicosola1.rsclient.R
import it.poliba.adicosola1.rsclient.databinding.ActivityLoginBinding
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel

class LoginActivity : AppCompatActivity() {


    private lateinit var layout: ActivityLoginBinding


    private val viewmodel by currentScope.viewModel<LoginViewModel>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewmodel.status.observe(
            this,
            Observer { Snackbar.make(layout.coordinatorLayout, it.get()?.message!!, Snackbar.LENGTH_LONG).show() })


        viewmodel.processing.observe(this, Observer {
            layout.progressLogin.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewmodel.userId.observe(this, Observer {
            val intent = Intent(this, PresenterActivity::class.java).putExtra("id", it.toString())
                .putExtra("name", layout.usernameText.text.toString())
            startActivity(intent)
        })

        layout.passwordText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            return@OnEditorActionListener when {
                TextUtils.isEmpty(layout.usernameText.text) -> {
                    layout.usernameText.error = "You must insert an username"
                    false
                }
                TextUtils.isEmpty(layout.passwordText.text) -> {
                    layout.passwordText.error = "You must insert a password"
                    false
                }

                actionId == EditorInfo.IME_ACTION_SEND -> {
                    viewmodel.login(layout.usernameText.text.toString(), layout.passwordText.text.toString())
                    true
                }

                else -> false
            }
        })
    }


}

