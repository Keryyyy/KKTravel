package com.hy.baseapp.net

import okhttp3.Dns
import java.lang.NullPointerException
import java.net.Inet4Address
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.ArrayList
import kotlin.Throws

/**
 * <pre>
 *
 * author: Hy
 * time  : 2019/12/4  14:54
 * desc  :
 *
</pre> *
 */
class ApiDns : Dns {
    @Throws(UnknownHostException::class)
    override fun lookup(hostname: String): List<InetAddress> {
        return if (hostname == null) {
            throw UnknownHostException("hostname == null")
        } else {
            try {
                val mInetAddressesList: MutableList<InetAddress> = ArrayList()
                val mInetAddresses = InetAddress.getAllByName(hostname)
                for (address in mInetAddresses) {
                    if (address is Inet4Address) {
                        mInetAddressesList.add(0, address)
                    } else {
                        mInetAddressesList.add(address)
                    }
                }
                mInetAddressesList
            } catch (var4: NullPointerException) {
                val unknownHostException = UnknownHostException("Broken system behaviour")
                unknownHostException.initCause(var4)
                throw unknownHostException
            }
        }
    }
}