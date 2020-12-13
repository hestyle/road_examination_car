package cn.hestyle.road_examination_car.task;

import java.net.Socket;

public abstract class BaseMessageTask {
    Socket socket;

    public abstract void execute();

    public  void setSocket(Socket socket){
        this.socket = socket;
    }
}
