package com.h2.app_list.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.h2.app_list.R
import com.h2.app_list.models.InstalledApp

class AppListAdapter(appList: ArrayList<InstalledApp>) : RecyclerView.Adapter<AppListHolder>() {
    private val _appList: ArrayList<InstalledApp> = appList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListHolder {
        return AppListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.app_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AppListHolder, position: Int) {
        val app = _appList[position]
        holder.appName!!.text = app._title
        holder.appPackageName!!.text = app._appPackageName
        holder.appIcon!!.setImageDrawable(app._icon)
        holder.appItem!!.setOnClickListener {
            Log.d("Clicked",app._appPackageName)
        }
    }

    override fun getItemCount(): Int {
        return _appList.size
    }
}

class AppListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var appName: TextView = itemView!!.findViewById(R.id.app_name)
    var appPackageName: TextView = itemView!!.findViewById(R.id.app_package_name)
    var appIcon: ImageView = itemView!!.findViewById(R.id.app_icon)
    var appItem : LinearLayout = itemView!!.findViewById(R.id.app_item)
}
