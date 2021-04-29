package com.permissionx.guolindev

import android.Manifest
import androidx.fragment.app.FragmentActivity

abstract class PermissonXOnJava {

    abstract fun callBack(allGranted: Boolean, deniedList: List<String>)

    fun request(activity: FragmentActivity,permissionStr:String){

        PermissionX.request(activity, permissionStr) { allGranted, deniedList ->
            callBack(allGranted,deniedList)
        }
    }

}