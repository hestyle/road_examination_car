package cn.hestyle.road_examination_car.message;

import java.net.Socket;

public interface BaseMessage {
    public void execute();
    public void setSocket(Socket socket);
}
