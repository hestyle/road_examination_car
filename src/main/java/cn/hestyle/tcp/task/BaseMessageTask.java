package cn.hestyle.tcp.task;

import java.net.Socket;

public abstract class BaseMessageTask {
    Socket socket;

    public abstract void execute();

    public  void setSocket(Socket socket){
        this.socket = socket;
    }
}
