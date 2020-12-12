package cn.hestyle.road_examination_car.server;

import cn.hestyle.road_examination_car.MainGui;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp服务器线程
 */
public class TcpServerThread extends Thread {
    public ServerSocket serverSocket = null;
    @Override
    public void run() {
        super.run();
        try {
            serverSocket = new ServerSocket(MainGui.port);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "tcp启动失败！");
        }
        System.out.println("------------TCP服务器启动成功，等待客户端的连接------------");
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            // 创建子线程，响应tcp请求
            MainGui.tcpRequestHandlerThread = new TcpRequestHandlerThread(socket);
            MainGui.tcpRequestHandlerThread.start();
            // 打印客户端ip
            InetAddress address = socket.getInetAddress();
            System.out.println("当前客户端的IP：" + address.getHostAddress());
        }
        System.out.println("TCP服务器已关闭！");
    }
}
