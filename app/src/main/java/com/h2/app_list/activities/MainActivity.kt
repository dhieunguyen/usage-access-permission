package com.h2.app_list.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.h2.app_list.R
import com.h2.app_list.adapters.AppListAdapter
import com.h2.app_list.models.InstalledApp


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appList: ArrayList<InstalledApp> = getNonSystemApps();
        val rcv = findViewById<RecyclerView>(R.id.app_list)
        val appListAdapter = AppListAdapter(appList)
        rcv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = appListAdapter
        }

    }
    private fun getNonSystemApps(): ArrayList<InstalledApp> {
        var appList: ArrayList<InstalledApp> = arrayListOf()
        try {
            val pm: PackageManager = applicationContext.packageManager
            val pList = pm.getInstalledPackages(0)
            for (i in pList.indices) {
                val packageInfo = pList[i]
                if (pm.getLaunchIntentForPackage(packageInfo.packageName) != null) {
                    val packageName: String = packageInfo.packageName
                    val appName: String =
                        (packageInfo.applicationInfo.loadLabel(pm) as String).trim { it <= ' ' }
                    val appIcon = packageInfo.applicationInfo.loadIcon(pm)
                    val app = InstalledApp(
                        packageName,
                        appName,
                        appIcon
                    )
                    appList.add(app);
                }
            }
        } catch (ex: Exception) {
        }
        return appList
    }
}
