package xyz.crafsed.gssmonitor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.crafsed.gssmonitor.databinding.CamerasFragment2Binding
import xyz.crafsed.gssmonitor.databinding.CamerasFragmentBinding
import java.lang.StringBuilder

class CamerasFragment() : Fragment() {
    private lateinit var binding: CamerasFragment2Binding
    lateinit var adapter: CamerasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cameras_fragment_2, container, false)
        val list = view.findViewById<RecyclerView>(R.id.cameras_list)
        adapter = CamerasAdapter(activity = activity as MainActivity)
        list.adapter = adapter
        list.layoutManager = GridLayoutManager(context, 3)
        binding = CamerasFragment2Binding.inflate(layoutInflater)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val prefEditor = activity?.getSharedPreferences("REC", Context.MODE_PRIVATE)
        val data = prefEditor?.getString("data", "")
        if ("" != data || ";" != data) {
            val parsedData = data?.split(";")
            parsedData?.forEach {
                if (!it.isBlank())
                    adapter.addElement(Camera("0", it))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val prefEditor = activity?.getSharedPreferences("REC", Context.MODE_PRIVATE)?.edit()
        val list = ArrayList<String>()
        val builder = StringBuilder()
        adapter.data.forEach {
            builder.append("${it.url};")
        }
        builder.removePrefix(";")
        Log.e("INFO", "onSaveInstanceState: ${builder.toString()}")
        prefEditor?.putString("data", builder.toString())
        prefEditor?.apply()
    }


}