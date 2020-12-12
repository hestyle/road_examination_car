package cn.hestyle.road_examination_car.server;

import cn.hestyle.road_examination_car.entity.MessageQueue;

public class MessageHandler<T> extends Thread{
    private static int count = 0;
    private boolean busy = false;
    private boolean stop = false;
    private MessageQueue<T> messageQueue;

    public MessageHandler(MessageQueue<T> messageQueue){
        this.messageQueue = messageQueue;

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
            T message = messageQueue.getMessage();
            if(message != null) {
                System.out.println(message);
                // 向考试端发送数据
            }
        }
        System.out.println(getName()+" end.");
    }
}
