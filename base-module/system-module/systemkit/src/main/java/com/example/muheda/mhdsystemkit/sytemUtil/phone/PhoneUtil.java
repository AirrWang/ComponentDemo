package com.example.muheda.mhdsystemkit.sytemUtil.phone;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by 13660 on 2018/11/15.
 */

public class PhoneUtil {

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    // ipv4地址
                    if (!inetAddress.isLoopbackAddress()
                            && InetAddressUtils.isIPv4Address(inetAddress
                            .getHostAddress())) {

                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
        }
        return "";
    }
}
