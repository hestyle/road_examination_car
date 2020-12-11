package cn.hestyle.road_examination_car.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class LocalNetworkHelp {
    private static final String WINDOWS_LOCAL_NETWORK_WLAN = "wlan";

    /**
     * 获取本地网络的ip地址（局域网ip，wlan无线网卡
     * @return      局域网ip地址
     */
    public static String getLocalIp() {
        Enumeration<NetworkInterface> networkInterfaceEnumeration = null;
        try {
            // 获得本机的所有网络接口
            networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
        while (networkInterfaceEnumeration.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
            // 获得与该网络接口绑定的 IP 地址，一般只有一个
            Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
            while (addressEnumeration.hasMoreElements()) {
                InetAddress address = addressEnumeration.nextElement();
                // 至需要ipv4
                if (address instanceof Inet4Address && networkInterface.getName().startsWith(LocalNetworkHelp.WINDOWS_LOCAL_NETWORK_WLAN)) {
                    return address.getHostAddress();
                }
            }
        }
        return null;
    }

    /**
     * 通过ip地址获取mac地址
     * @param ipAddress     ip地址
     * @return              mac地址
     */
    public static String getMacAddressByIp(String ipAddress) {
        try {
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getByName(ipAddress));
            byte[] mac = networkInterface.getHardwareAddress();
            // 将mac地址转换为字符串
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    stringBuilder.append("-");
                }
                // mac[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(mac[i] & 0xFF);
                stringBuilder.append(s.length() == 1 ? 0 + s : s);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
