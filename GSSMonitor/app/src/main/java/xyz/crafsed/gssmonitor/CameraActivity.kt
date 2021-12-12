package xyz.crafsed.gssmonitor

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stream_fragment)

        val web = findViewById<WebView>(R.id.webview)
        web.settings.apply {
            allowFileAccess = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            javaScriptEnabled = true
            loadWithOverviewMode = true
        }

        val url = getSharedPreferences("GSS",Context.MODE_PRIVATE).getString("url","<empty>")
        web.loadUrl(url!!)
    }
}