package it.poliba.adicosola1.rsclient.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.poliba.adicosola1.rsclient.common.authentication.IAuth
import it.poliba.adicosola1.rsclient.common.ui.Error
import it.poliba.adicosola1.rsclient.common.ui.EventHandler
import it.poliba.adicosola1.rsclient.common.ui.toWrapper
import it.poliba.adicosola1.rsclient.common.util.IConnectivity
import org.koin.core.KoinComponent
import org.koin.core.inject

//FIXME Avoid IAuth<T>
class LoginViewModel(val auth: IAuth<Long>) : ViewModel(), KoinComponent {

    private val connectivityHelper: IConnectivity by inject()

    val status = MutableLiveData<EventHandler>()
    val processing = MutableLiveData<Boolean>(false)
    val userId = MutableLiveData<Long>()

    fun login(username: String, password: String) {

        /* Check network */
        if (!connectivityHelper.isOnline()) {
            status.postValue(Error("No network available").toWrapper())
            return
        }

        processing.value = true //setValue
        auth.connect(username, password)
            .doOnNext { response ->
                processing.postValue(false)
                userId.postValue(response.body)
            }
            .subscribe()
    }
}