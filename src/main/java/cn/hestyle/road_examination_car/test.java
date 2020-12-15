package cn.hestyle.road_examination_car;

import cn.hestyle.tcp.TcpRequestMessage;
import cn.hestyle.tcp.TcpResponseMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class test {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("192.168.31.38", 10101);

            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            TcpRequestMessage start = new TcpRequestMessage();
            start.setTypeName(TcpResponseMessage.RESPONSE_CHECK_IP_AND_MAC);
            oos = new ObjectOutputStream(socket.getOutputStream());
            // 发start
            oos.writeObject(start);
            oos.flush();
            // 读start
            ois = new ObjectInputStream(socket.getInputStream());
            System.err.println(((TcpResponseMessage) ois.readObject()).toString());

            // 发ip获取请求
            oos.writeObject(start);
            oos.flush();

            // 读发来的ip
            TcpResponseMessage ipmac = (TcpResponseMessage) ois.readObject();

            // 发ok
            TcpRequestMessage ok  = new TcpRequestMessage();
            ok.setTypeName("true");
            oos.writeObject(ok);
            oos.flush();


            Scanner in = new Scanner(System.in);
            Integer end = in.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
