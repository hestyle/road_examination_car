package cn.hestyle.road_examination_car;

import cn.hestyle.road_examination_car.entity.MessageTaskQueue;
import cn.hestyle.road_examination_car.woker.MessageHandler;

import java.io.IOException;
import java.net.Socket;

public class GuiApp {
    public static MessageTaskQueue messageTaskQueue = new MessageTaskQueue();

    public static void main(String[] args){
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 12325);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageHandler messageHandler = new MessageHandler(messageTaskQueue, client);
        messageHandler.start();

        CarGui carGui = new CarGui();
        carGui.setVisible(true);
    }
}
