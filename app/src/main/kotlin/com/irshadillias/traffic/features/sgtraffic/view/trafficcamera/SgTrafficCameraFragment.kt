package com.irshadillias.traffic.features.sgtraffic.view.trafficcamera

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.irshadillias.traffic.R
import com.irshadillias.traffic.core.extension.failure
import com.irshadillias.traffic.core.extension.observe
import com.irshadillias.traffic.core.extension.viewModel
import com.irshadillias.traffic.core.navigation.Navigator
import com.irshadillias.traffic.core.platform.BaseFragment
import com.irshadillias.traffic.core.utilities.Constants
import com.irshadillias.traffic.features.sgtraffic.common.SgTrafficUtilities
import com.irshadillias.traffic.features.sgtraffic.view.ui.CustomInfoWindowOfMarker
import com.irshadillias.traffic.features.sgtraffic.model.Api_info
import com.irshadillias.traffic.features.sgtraffic.model.Cameras
import com.irshadillias.traffic.features.sgtraffic.model.GetTrafficResponse
import com.irshadillias.traffic.features.sgtraffic.model.Location
import kotlinx.android.synthetic.main.fragment_sgtrafficmap.*
import javax.inject.Inject


class SgTrafficCameraFragment : BaseFragment(), OnMapReadyCallback {
    @Inject
    lateinit var navigator: Navigator
    private lateinit var trafficViewModel: SgTrafficCameraViewModel
    private lateinit var mMap: GoogleMap
    private var isZoomedIn = false;
    private val handler = Handler()
    val markerMap : MutableMap<MarkerOptions, Cameras> = HashMap()
    val runnable: Runnable = run {
        Runnable {
            fetchTrafficCameraDetail()
            handler.postDelayed(runnable, Constants.ONE_MINUTE)
        }
    }

    override fun layoutId() = R.layout.fragment_sgtrafficmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        trafficViewModel = viewModel(viewModelFactory) {
            observe(sgTraffic, ::renderCamera)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.sgTrafficMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun fetchTrafficCameraDetail () {
        trafficViewModel.loadCameraDetail()
    }

    private fun renderCamera(ltaResponse: GetTrafficResponse?) {
        run {
            checkReadyThen {
                setMarkerMap(ltaResponse ?: GetTrafficResponse(emptyList(), Api_info("")))
                addMarkers(markerMap)
                ltaResponse?.let {
                    if(!isZoomedIn){
                        zoomToPin(it.items[0].cameras[0].location)
                        isZoomedIn = true
                    }
                }
                val mTimeStamp = ltaResponse?.let { it.items[0].timestamp }
                if (timestamp.visibility == View.INVISIBLE) {
                    timestamp.visibility = View.VISIBLE
                }
                val prefix = "Last updated : "
                if(!timestamp.text.equals(prefix + SgTrafficUtilities.changeServerDateFormate(mTimeStamp))){
                    timestamp.text = prefix + SgTrafficUtilities.changeServerDateFormate(mTimeStamp)
                }
                Toast.makeText(activity, "refresh of traffic data completed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*
     * Map rendering Section
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setInfoWindowAdapter(CustomInfoWindowOfMarker(this))
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isScrollGesturesEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        fetchTrafficCameraDetail()
    }

    private fun checkReadyThen(actionDo : () -> Unit) {
        if (!::mMap.isInitialized) {
            Toast.makeText(activity, "Map is not Initialized !!!", Toast.LENGTH_SHORT).show()
        } else {
            actionDo()
        }
    }

    private fun addMarkers(map : Map<MarkerOptions, Cameras>){
        mMap.clear()
        map.forEach{(markerOptions, camera) ->
            run {
                val marker = mMap.addMarker(markerOptions)
                marker.tag = camera
            }
        }
    }
    private fun setMarkerMap(ltaData : GetTrafficResponse){
        val cameraList = ltaData.items.get(0).cameras;
        for ( camera in cameraList){
            val options = getMarkerOptions(camera)
            markerMap[options] = camera
        }
    }

    private fun getMarkerOptions(cameras: Cameras): MarkerOptions{
        val gps = LatLng(cameras.location.latitude, cameras.location.longitude)
        val options = MarkerOptions()
        options.title(cameras.timestamp)
        options.position(gps)
        return options
    }

    private fun zoomToPin(location: Location){
        val latLng = LatLng(location.latitude, location.longitude)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, Constants.ONE_MINUTE)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

}