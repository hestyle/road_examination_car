package cn.hestyle.road_examination_car.message;

import java.net.Socket;
import java.util.List;

public class OperationsMessage<T> implements BaseMessage {
    List<T> operationList;
    Socket socket;

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OperationsMessage(List<T> operationList){
        this.operationList = operationList;
        this.socket = socket;
    }
    @Override
    public void execute() {
        if(socket == null)
            System.out.println(operationList.toString());
    }
}
