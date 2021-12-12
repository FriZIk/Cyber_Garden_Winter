package xyz.crafsed.gssmonitor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.AppBarLayout

import android.widget.LinearLayout

import android.widget.EditText

import android.view.LayoutInflater
import android.view.View
import android.widget.Button


class MainActivity : IProjectDialFrag, AppCompatActivity() {
    lateinit var cameras: CamerasFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GSSMonitor)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        super.onStart()
        cameras = CamerasFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame, cameras).commit()
    }

    var id = 0
    fun createCamera(view: android.view.View) {
        CamerasDialog().showNow(supportFragmentManager, "dialog")
    }

    fun removeCamera(view: android.view.View) {
        cameras.adapter.removeElement(Integer.parseInt(view.tooltipText.toString()))
    }

    fun openCamera(camera: Camera) {
        getSharedPreferences("GSS", Context.MODE_PRIVATE).edit().putString("url",camera.url).putString("id",camera.id).apply()
        startActivity(Intent(this, CameraActivity::class.java))
    }

    override fun onDialogPositiveClick(dialog: DialogFragment?) {
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(xyz.crafsed.gssmonitor.R.layout.dialog_fragment, null)
        cameras.adapter.addElement(Camera(id++.toString(), getSharedPreferences("URLS", Context.MODE_PRIVATE).getString("url", "<none>")!!))
    }

    override fun onDialogNegativeClick(dialog: DialogFragment?) {
        return
    }

    fun openCamera(view: android.view.View) {
        getSharedPreferences("GSS", Context.MODE_PRIVATE).edit().putString("url","http://zgame.gq:45005/onlystream").putString("id","1").apply()
        startActivity(Intent(this, CameraActivity::class.java))
    }
    /*
    * Какие фрагменты имеются?
    * Фрагмент мо списком камер
    * Фграгмент с одной касмерой и правой панелькой
    * Фрагмент с камерой на весь экран
    *
    *
    * Что надо сделать шаг 1:
    * Переходы между фрагментами
    * Взаимодействие с каким-нибудь стримом
    * Надо чтоб при нажатии наминикамеру открыал именно там камера которая нужна
    * */

}