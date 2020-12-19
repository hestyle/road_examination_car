package cn.hestyle.road_examination_car.task;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SingleOperationMessageTask<T> extends BaseMessageTask {
    T data;
    public SingleOperationMessageTask(T data){
        this.data = data;
    }

    @Override
    public void execute() {
        if(socket == null)
            System.out.println(data.toString());
        else {
            synchronized (socket){
                // 套接字通信
                try {
                    String msg = data.toString();
                    Integer length = 0;
                    byte[] b = new byte[1024];

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(msg.getBytes());
                    OutputStream outputStream = socket.getOutputStream();
                    while (byteArrayInputStream.available() > 0){
                        length = byteArrayInputStream.read(b);
                        outputStream.write(b, 0, length);
                        outputStream.flush();
                    }
                    byteArrayInputStream.close();
                    System.out.println("发送:"+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
