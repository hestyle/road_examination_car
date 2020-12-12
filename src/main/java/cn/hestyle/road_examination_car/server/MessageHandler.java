package cn.hestyle.road_examination_car.server;

import cn.hestyle.road_examination_car.message.BaseMessage;
import cn.hestyle.road_examination_car.entity.MessageQueue;

import java.net.Socket;

public class MessageHandler extends Thread{
    private static int count = 0;
    private boolean busy = false;
    private boolean stop = false;
    private MessageQueue messageQueue;

    String targetIP;
    Integer targetPort;
    Socket socket;

    public MessageHandler(MessageQueue messageQueue, String targetIP, Integer targetPort){
        this.messageQueue = messageQueue;
        this.targetIP = targetIP;
        this.targetPort = targetPort;
        if(targetIP != null && targetIP != ""){
            try {
                socket = new Socket(targetIP, targetPort);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        //与考试端建立连接
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
        System.out.println("按钮消息发送线程 start.");
        while(!stop) {
            BaseMessage message = messageQueue.getMessage();
            if(message != null) {
                System.out.println(message);
                message.setSocket(socket);
                message.execute();
            }
        }
        System.out.println(getName()+" end.");
    }
}
