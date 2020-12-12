package cn.hestyle.road_examination_car.entity;

import java.util.LinkedList;
import java.util.List;

public class MessageQueue<T> {
    private List<T> infoList = new LinkedList<>();
    public synchronized T getMessage() {
        while(infoList.size() == 0) {
            try {
                this.wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        return (T) infoList.remove(0);
    }

    public synchronized void putMessage(T message) {
        infoList.add(message);
        this.notifyAll();
    }
}
