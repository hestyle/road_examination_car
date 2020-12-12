package cn.hestyle.road_examination_car.server;

import cn.hestyle.road_examination_car.MainGui;

import java.io.IOException;
import java.net.Socket;

/**
 * tcp请求处理线程
 */
public class TcpRequestHandlerThread extends Thread {
    private Socket socket = null;

    public TcpRequestHandlerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            // 获取输入流，并读取客户端信息
            int length = 0;
            boolean isContinue = true;
            byte[] buffer = new byte[1024];
            while (isContinue) {
                // 发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
                socket.sendUrgentData(0xff);
                while((length = socket.getInputStream().read(buffer)) != -1){//循环读取客户端的信息
                    String receivedData = new String(buffer, 0, length);
                    System.out.println("我是服务器，客户端说："+ receivedData);
                    // 读到close，则关闭
                    if ("close".equals(receivedData)) {
                        isContinue = false;
                        break;
                    }
                }
                sleep(200);
            }
            socket.shutdownInput();//关闭输入流
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            //关闭资源
            try {
                if(socket != null) {
                    System.out.println("客户端【" + socket.getInetAddress().toString() + "】已断开连接！");
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainGui.tcpRequestHandlerThread = null;
        }
    }
}
