package cn.hestyle.road_examination_car;

import cn.hestyle.road_examination_car.entity.MessageQueue;
import cn.hestyle.road_examination_car.server.MessageHandler;

public class GuiApp {
    public static MessageQueue<String> messageQueue = new MessageQueue<>();

    public static void main(String[] args){
        MessageHandler<String> messageHandler = new MessageHandler<>(messageQueue);
        messageHandler.start();

        CarGui carGui = new CarGui();
        carGui.setVisible(true);
    }
}
