package com.h2.app_list.adapters

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.h2.app_list.R
import com.h2.app_list.models.InstalledApp
import com.h2.app_list.utils.Utils


class AppListAdapter(appList: ArrayList<InstalledApp>,context: Context) : RecyclerView.Adapter<AppListHolder>() {
    private val _appList: ArrayList<InstalledApp> = appList
    private val _context: Context = context
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
            if(!Utils.isAccessGranted(_context)){
                val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                _context.startActivity(intent)
            }
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
