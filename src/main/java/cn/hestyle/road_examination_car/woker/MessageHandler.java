package cn.hestyle.road_examination_car.woker;

import cn.hestyle.road_examination_car.task.BaseMessageTask;
import cn.hestyle.road_examination_car.entity.MessageQueue;

import java.net.Socket;

public class MessageHandler extends Thread{
    private static int count = 0;
    private boolean busy = false;
    private boolean stop = false;
    private MessageQueue messageQueue;

    private String targetIP;
    private Integer targetPort;
    private Socket socket;

    public MessageHandler(MessageQueue messageQueue, String targetIP, Integer targetPort){
        this.messageQueue = messageQueue;
        this.targetIP = targetIP;
        this.targetPort = targetPort;
        if(targetIP != null && targetIP != ""){
            try {
                socket = new Socket(targetIP, targetPort);
                //与考试端建立连接
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public MessageHandler(MessageQueue messageQueue, Socket socket){
        this.messageQueue = messageQueue;
        this.socket = socket;
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
        System.out.println("按钮消息处理线程 start.");
        while(!stop) {
            if(messageQueue.size() == 0){
                try {
                    synchronized (messageQueue){
                        if(messageQueue.size() == 0)
                            messageQueue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            BaseMessageTask message = messageQueue.getMessage();
            if(message != null) {
                message.setSocket(socket);
                message.execute();
            }
        }
        System.out.println(getName()+" end.");
    }
}
