package cn.hestyle.road_examination_car;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class test {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("192.168.31.38", 10101);
            Scanner in = new Scanner(System.in);
            Integer end = in.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
