package com.hy.baseapp.common.utils

import com.blankj.utilcode.util.ActivityUtils
import com.hjq.permissions.OnPermission
import com.hjq.permissions.XXPermissions

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/06/23
 *     desc  : 权限申请封装
 *
 * </pre>
 */

inline fun requestPermission(
    vararg permissions: String,
    crossinline hasPermission: (granted: MutableList<String>?, all: Boolean) -> Unit = { _, _ -> },
    crossinline noPermission: (denied: MutableList<String>?, never: Boolean) -> Unit = { _, _ -> }
) {
    XXPermissions.with(ActivityUtils.getTopActivity()).permission(permissions)
        .request(object : OnPermission {
            override fun hasPermission(granted: MutableList<String>?, all: Boolean) {
                hasPermission.invoke(granted,all)
            }

            override fun noPermission(denied: MutableList<String>?, never: Boolean) {
                noPermission.invoke(denied,never)
            }
        })

}