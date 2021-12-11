package xyz.crafsed.gssmonitor

import android.content.Intent
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

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)
        cameras = CamerasFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame, cameras).commit()
    }

    var id = 0
    fun createCamera(view: android.view.View) {
        CamerasDialog().showNow(supportFragmentManager, "dialog")
//        cameras.adapter.addElement(Camera(id++.toString(), "http://zgame.gq:45005/onlystream"))
    }

    fun removeCamera(view: android.view.View) {
        cameras.adapter.removeElement(Integer.parseInt(view.tooltipText.toString()))
    }

    fun openCamera(camera: Camera) {
        startActivity(Intent(this, CameraActivity::class.java))
    }

    override fun onDialogPositiveClick(dialog: DialogFragment?) {
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(xyz.crafsed.gssmonitor.R.layout.dialog_fragment, null)
        val ip = view.findViewById<EditText>(xyz.crafsed.gssmonitor.R.id.ip)
        cameras.adapter.addElement(Camera(id++.toString(), "http://zgame.gq:45005/onlystream"))
//        cameras.adapter.addElement(Camera(id++.toString(), "${ip.text}"))
    }

    override fun onDialogNegativeClick(dialog: DialogFragment?) {
        return
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