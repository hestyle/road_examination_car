package cn.hestyle.road_examination_car.task;

import java.net.Socket;

public class SingleOperationMessageTask<T> extends BaseMessageTask {
    T data;
    Socket socket;
    public SingleOperationMessageTask(T data){
        this.data = data;
        this.socket = socket;
    }

    @Override
    public void execute() {
        if(socket == null)
            System.out.println(data.toString());
    }
}
