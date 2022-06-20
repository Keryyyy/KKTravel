package com.hy.baseapp.net

import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import javax.net.ssl.X509TrustManager

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2022/04/25
 *     desc  : 信任所有证书
 *
 * </pre>
 */
class TrustAllManager: X509TrustManager {
    override fun checkClientTrusted(
        chain: Array<out java.security.cert.X509Certificate>?,
        authType: String?
    ) {

    }

    override fun checkServerTrusted(
        chain: Array<out java.security.cert.X509Certificate>?,
        authType: String?
    ) {

    }

    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
        return emptyArray()
    }
}

private class TrustAllHostnameVerifier : HostnameVerifier {
    override fun verify(hostname: String?, session: SSLSession?): Boolean = true
}