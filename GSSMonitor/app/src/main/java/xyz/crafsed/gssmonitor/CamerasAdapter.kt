package xyz.crafsed.gssmonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CamerasAdapter(val activity: MainActivity) : RecyclerView.Adapter<CamerasViewHolder>() {
    val data: ArrayList<Camera> = ArrayList<Camera>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamerasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_placeholder_fragment, parent, false)

        return CamerasViewHolder(view)
    }

    override fun onBindViewHolder(holder: CamerasViewHolder, position: Int) {

        if (position == itemCount-1) {
            holder.cameraImage.visibility = View.INVISIBLE
            holder.plusButton.visibility = View.VISIBLE
            holder.plusButton.isClickable = true
        } else {
            holder.cameraImage.visibility = View.VISIBLE
            holder.itemView.setOnClickListener{
                activity.openCamera(data[position])
            }
            holder.plusButton.visibility = View.GONE
            holder.plusButton.isClickable = false
            holder.cameraId.text = data[position].id

            holder.cameraImage.loadUrl(data[position].url)
        }
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    fun addElement(camera: Camera) {
        data.add(camera)
        notifyDataSetChanged()
    }

    fun removeElement(camera: Camera) {
        data.remove(camera)
        notifyDataSetChanged()
    }

    fun removeElement(id: Int) {
        data.removeAt(id)
        notifyDataSetChanged()
    }
}
