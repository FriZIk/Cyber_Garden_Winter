package xyz.crafsed.gssmonitor

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.app.Activity
import android.content.Context
import android.widget.EditText


class CamerasDialog : DialogFragment() {
    var ok = false
    private var iProjDialFrag: IProjectDialFrag? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val createProjectAlert: AlertDialog.Builder = AlertDialog.Builder(activity)
        createProjectAlert.setTitle("Camera configuration");
        createProjectAlert.setView(layoutInflater.inflate(R.layout.dialog_fragment, null))
            .setPositiveButton("OK") { _, _ ->
                context?.getSharedPreferences("URLS", Context.MODE_PRIVATE)?.edit()
                    ?.putString("url", dialog?.findViewById<EditText>(R.id.ip)?.text.toString())
                    ?.commit()
                iProjDialFrag!!.onDialogPositiveClick(this)
            }
            .setNegativeButton("Cancel") { _, _ ->
                iProjDialFrag!!.onDialogNegativeClick(this)
            }
        return createProjectAlert.create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        iProjDialFrag = activity as IProjectDialFrag
    }
}

interface IProjectDialFrag {
    fun onDialogPositiveClick(dialog: DialogFragment?)
    fun onDialogNegativeClick(dialog: DialogFragment?)
}