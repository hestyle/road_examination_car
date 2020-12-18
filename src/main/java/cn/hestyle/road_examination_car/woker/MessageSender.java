package cn.hestyle.road_examination_car.woker;

import cn.hestyle.road_examination_car.entity.MessageTaskQueue;
import cn.hestyle.tcp.TcpMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessageSender extends Thread{
    private static int count = 0;
    private boolean busy = false;
    private boolean stop = false;
    private MessageTaskQueue messageTaskQueue;

    private ObjectOutputStream oos;

    public MessageSender(MessageTaskQueue messageTaskQueue, ObjectOutputStream oos){
        this.messageTaskQueue = messageTaskQueue;
        this.oos = oos;
    }

    public void shutdown() {
        stop = true;
        this.interrupt();
        try {
            this.join();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("消息发送 start");
        while(!stop) {
            TcpMessage message = messageTaskQueue.getMessage();
            if(message != null) {
                if(oos != null){
                    try {
                        System.out.println("发送消息:"+message.toString());
                        oos.writeObject(message);
                        oos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.err.println("发送数据给考试端失败");
                    }
                }else {
                    System.out.println(message.toString());
                }
            }
        }
        System.out.println(getName()+" end.");
    }
}
