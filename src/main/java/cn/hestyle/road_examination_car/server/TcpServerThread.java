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

    public static void main(String[] args){
        try {
            Integer tmpPort = 12325;
            if (tmpPort < 0 || tmpPort > 65535) {
                System.err.println("TCP服务启动失败，端口号【" + tmpPort + "】非法！");
                return;
            } else if (tmpPort < 1024) {
                System.err.println("TCP服务启动失败，端口号【" + tmpPort + "】为系统保留端口号，请修改为1024~65535之间！");
                return;
            }
            MainGui.port = tmpPort;
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }

        // 启动tcp服务器
        TcpServerThread tcpServerThread = new TcpServerThread();
        tcpServerThread.start();
        // disable启动tcp服务的按钮,enable停止tcp服务的按钮
        System.err.println("提示：TCP服务运行中，正在等待连接...");
    }
}
