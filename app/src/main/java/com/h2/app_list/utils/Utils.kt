package com.h2.app_list.utils

import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService


class Utils {
    companion object {
        fun isAccessGranted( context: Context): Boolean {
            return try {
                val packageManager: PackageManager = context.packageManager
                val applicationInfo = packageManager.getApplicationInfo(context.packageName, 0)
                val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager?
                var mode = 0
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    mode = appOpsManager!!.checkOpNoThrow(
                        AppOpsManager.OPSTR_GET_USAGE_STATS,
                        applicationInfo.uid, applicationInfo.packageName
                    )
                }
                mode == AppOpsManager.MODE_ALLOWED
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }
    }

}