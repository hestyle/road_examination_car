package cn.hestyle.road_examination_car.entity;

import cn.hestyle.tcp.TcpMessage;

import java.util.LinkedList;
import java.util.List;

public class MessageTaskQueue {
    private List<TcpMessage> tcpMessageList = new LinkedList<>();
    public synchronized TcpMessage getMessage() {
        while(tcpMessageList.size() == 0) {
            try {
                this.wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        return tcpMessageList.remove(0);
    }

    public synchronized void putMessage(TcpMessage message) {
        tcpMessageList.add(message);
        this.notifyAll();
    }

    public Integer size(){
        return this.tcpMessageList.size();
    }
}
