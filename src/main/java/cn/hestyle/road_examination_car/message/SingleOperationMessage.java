package cn.hestyle.road_examination_car.message;

import java.net.Socket;

public class SingleOperationMessage<T> implements BaseMessage {
    T data;
    Socket socket;
    public SingleOperationMessage(T data){
        this.data = data;
        this.socket = socket;
    }

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void execute() {
        if(socket == null)
            System.out.println(data.toString());
    }
}
