package cn.hestyle.road_examination_car.task;

import java.net.Socket;
import java.util.List;

public class OperationsMessageTask<T> extends BaseMessageTask {
    List<T> operationList;
    Socket socket;

    @Override
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OperationsMessageTask(List<T> operationList){
        this.operationList = operationList;
        this.socket = socket;
    }

    @Override
    public void execute() {
        if(socket == null)
            System.out.println(operationList.toString());
    }
}
