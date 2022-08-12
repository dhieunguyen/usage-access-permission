package com.h2.app_list.models

import android.graphics.drawable.Drawable

class InstalledApp(appPackageName:String,title:String,icon:Drawable) {
    var _appPackageName: String = appPackageName
    var _title: String = title
    var _icon = icon
    override fun toString(): String {
        return _title;
    }
}
