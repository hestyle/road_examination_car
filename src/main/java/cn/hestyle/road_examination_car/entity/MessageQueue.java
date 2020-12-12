package cn.hestyle.road_examination_car.entity;

import cn.hestyle.road_examination_car.message.BaseMessage;

import java.util.LinkedList;
import java.util.List;

public class MessageQueue {
    private List<BaseMessage> infoList = new LinkedList<>();
    public synchronized BaseMessage getMessage() {
        while(infoList.size() == 0) {
            try {
                this.wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        return infoList.remove(0);
    }

    public synchronized void putMessage(BaseMessage message) {
        infoList.add(message);
        this.notifyAll();
    }
}
