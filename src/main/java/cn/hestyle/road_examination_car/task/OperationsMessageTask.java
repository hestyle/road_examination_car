package cn.hestyle.tcp.task;

import java.util.List;

public class OperationsMessageTask<T> extends BaseMessageTask {
    List<T> operationList;

    public OperationsMessageTask(List<T> operationList){
        this.operationList = operationList;
    }

    @Override
    public void execute() {
        System.err.println(socket.toString());
        if(socket == null)
            System.out.println(operationList.toString());
        else {
            synchronized (socket){
                // 套接字通信
            }
        }
    }
}
