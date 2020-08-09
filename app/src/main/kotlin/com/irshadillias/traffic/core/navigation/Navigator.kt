package com.irshadillias.traffic.core.navigation

import android.content.Context
import android.view.View
import com.irshadillias.traffic.features.login.Authenticator
import com.irshadillias.traffic.features.login.LoginActivity
import com.irshadillias.traffic.features.sgtraffic.SgTrafficActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.callingIntent(context))

    private fun showTrafficCamera(context: Context) = context.startActivity(SgTrafficActivity.callingIntent(context))

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showTrafficCamera(context)
            false -> showLogin(context)
        }
    }

    class Extras(val transitionSharedElement: View)
}


