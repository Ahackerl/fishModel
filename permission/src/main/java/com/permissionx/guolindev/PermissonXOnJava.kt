package com.permissionx.guolindev

import androidx.fragment.app.FragmentActivity

abstract class PermissonXOnJava {

    abstract fun callBack(allGranted: Boolean, deniedList: List<String>)

    fun request(activity: FragmentActivity,vararg permissionStr:String){

        PermissionX.request(activity, *permissionStr) { allGranted, deniedList ->
            callBack(allGranted,deniedList)
        }
    }

}