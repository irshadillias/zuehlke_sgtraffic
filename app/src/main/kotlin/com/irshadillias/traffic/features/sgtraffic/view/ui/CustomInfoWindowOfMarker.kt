package com.irshadillias.traffic.features.sgtraffic.view.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.irshadillias.traffic.R
import com.irshadillias.traffic.core.platform.BaseFragment
import com.irshadillias.traffic.features.sgtraffic.model.Cameras

class CustomInfoWindowOfMarker : GoogleMap.InfoWindowAdapter {
    var context: Fragment
    constructor(fragment: BaseFragment) {
        context = fragment
    }
    lateinit var markerItem: Marker
    lateinit var item: Cameras
    var map: MutableMap<String, Boolean> = HashMap()

    override fun getInfoContents(marker: Marker): View {
        val cardView = context.layoutInflater.inflate(R.layout.view_marker_window, null)
        val imageView = cardView.findViewById<ImageView>(R.id.imageView4)
        val cameras = marker.tag as Cameras
        item = cameras
        markerItem = marker
        if (map.get(cameras.image) == null) {
            Glide.with(context)
                .load(cameras.image)
                .override(600, 400)
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        map.put(cameras.image, true)
                        markerItem.showInfoWindow()
                        return false
                    }
                }).into(imageView as ImageView)
        } else {
            var options = RequestOptions()
            options.onlyRetrieveFromCache(true)
            Glide.with(context)
                .apply {
                    options
                }
                .load(cameras.image)
                .placeholder(R.drawable.progress_animation)
                .override(600, 400)
                .centerCrop()
                .into(imageView as ImageView)
        }

        return cardView
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }
}
