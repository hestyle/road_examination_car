package cn.hestyle.road_examination_car.task;

import java.net.Socket;

public abstract class BaseMessageTask {
    Socket socket;

    public abstract void execute();
    public abstract void setSocket(Socket socket);
}
