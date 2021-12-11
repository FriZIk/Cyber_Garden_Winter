package xyz.crafsed.gssmonitor

import android.view.TextureView
import android.view.View
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CamerasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cameraId: TextView
    val plusButton: ImageButton
    val cameraImage: WebView
    init {
        cameraId = itemView.findViewById(R.id.camera_id)
        plusButton = itemView.findViewById(R.id.plus_button)
        cameraImage = itemView.findViewById(R.id.camera_image)
        cameraImage.settings.apply {
            allowFileAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            javaScriptEnabled = true
            loadWithOverviewMode = true
        }
//        cameraImage.focusable = View.NOT_FOCUSABLE
    }
}