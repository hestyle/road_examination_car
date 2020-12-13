package cn.hestyle.road_examination_car.entity;

import cn.hestyle.road_examination_car.task.BaseMessageTask;

import java.util.LinkedList;
import java.util.List;

public class MessageTaskQueue {
    private List<BaseMessageTask> infoList = new LinkedList<>();
    public synchronized BaseMessageTask getMessage() {
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

    public synchronized void putMessage(BaseMessageTask message) {
        infoList.add(message);
        this.notifyAll();
    }

    public Integer size(){
        return this.infoList.size();
    }
}
