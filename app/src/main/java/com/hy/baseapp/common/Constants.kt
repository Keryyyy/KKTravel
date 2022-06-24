package com.hy.baseapp.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/06/23
 *     desc  : some constants in app use
 *
 * </pre>
 */

/**********************
 *
 *  Intent传值默认Key
 *
 **********************/


/**********************
 *
 *  Activity跳转请求码
 *
 **********************/
const val REQUEST_CODE_88 = 0x000088
const val REQUEST_CODE_89 = 0x000089
const val REQUEST_CODE_90 = 0x000090
const val REQUEST_CODE_91 = 0x000091
const val REQUEST_CODE_92 = 0x000092
const val REQUEST_CODE_93 = 0x000093
const val REQUEST_CODE_94 = 0x000094
const val REQUEST_CODE_95 = 0x000095
const val REQUEST_CODE_96 = 0x000096
const val REQUEST_CODE_97 = 0x000097
const val REQUEST_CODE_500 = 0x0000500
const val REQUEST_CODE_501 = 0x0000501
const val REQUEST_CODE_21 = 0X121
const val RESULT_CODE_SUCCESS = 0x024
const val RESULT_CODE_KEY_2 = 0x025


/**********************
 *
 *  Key-Value Sealed Class
 *
 **********************/

sealed class KV{

    sealed class DataStore{
        companion object {
            const val KEY_DEFAULT = "user_preferences"
        }

        sealed class UserInfo{
            companion object {
                const val USER_INFO = "user_info"
            }
        }
    }

    /**
     * startActivity Intent Key
     */
    sealed class Intent{
        companion object {
            const val DEFAULT_KEY_1 = "defaultIntent1"
            const val DEFAULT_KEY_2 = "defaultIntent2"
            const val DEFAULT_KEY_3 = "defaultIntent3"
            const val DEFAULT_KEY_4 = "defaultIntent4"
        }
    }

}