package com.doublesymetrymusic.data.datasource

import java.net.InetSocketAddress
import java.net.Socket

object NetworkUtils {

  /**
   * checks with google DNS - sometimes even with wifi-on there is no internet so checking on that alone could be misleading
   */
  fun hasInternetConnection(): Boolean {
    try {
      // Connect to Google DNS to check for connection
      val timeoutMs = 1500
      val socket = Socket()
      val socketAddress = InetSocketAddress("8.8.8.8", 53)

      socket.connect(socketAddress, timeoutMs)
      socket.close()

      return true
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return false
  }
}