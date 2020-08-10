package com.irshadillias.traffic.features.sgtraffic

import android.content.Context
import android.content.Intent
import com.irshadillias.traffic.R
import com.irshadillias.traffic.core.platform.BaseActivity
import com.irshadillias.traffic.core.platform.BaseFragment
import com.irshadillias.traffic.features.sgtraffic.view.trafficcamera.SgTrafficCameraFragment

class SgTrafficActivity : BaseActivity() {
    companion object {
       fun callingIntent(context: Context) = Intent(context, SgTrafficActivity::class.java)
    }
    override fun fragment(): BaseFragment = SgTrafficCameraFragment()

    override fun onRestart() {
        super.onRestart()
        var childfrag = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if(childfrag is SgTrafficCameraFragment)
            childfrag.onRestartActivity()
    }

}