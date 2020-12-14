/*
 * Created by JFormDesigner on Fri Dec 11 11:48:06 CST 2020
 */

package cn.hestyle.road_examination_car;

import java.awt.event.*;

import cn.hestyle.road_examination_car.server.TcpRequestHandlerThread;
import cn.hestyle.road_examination_car.server.TcpServerThread;
import cn.hestyle.road_examination_car.util.LocalNetworkHelp;
import cn.hestyle.road_examination_car.woker.MessageHandler;
import com.sun.corba.se.spi.activation.Server;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

/**
 * @author hestyle
 */
public class MainGui extends JFrame {
    /**tcp服务监听的端口号*/
    public static Integer port = null;
    /**tcp服务器线程*/
    private static TcpServerThread tcpServerThread = null;
    /**tcp请求处理线程*/
    public static TcpRequestHandlerThread tcpRequestHandlerThread = null;
    public MainGui() {
        initComponents();
    }

    /**
     * startTcpButton点击事件
     * @param e     点击事件
     */
    private void startTcpButtonActionPerformed(ActionEvent e) {
        // 端口号字符串转数字
        try {
            Integer tmpPort = Integer.valueOf(this.portTextField.getText());
            if (tmpPort < 0 || tmpPort > 65535) {
                JOptionPane.showMessageDialog(this, "TCP服务启动失败，端口号【" + tmpPort + "】非法！");
                return;
            } else if (tmpPort < 1024) {
                JOptionPane.showMessageDialog(this, "TCP服务启动失败，端口号【" + tmpPort + "】为系统保留端口号，请修改为1024~65535之间！");
                return;
            }
            MainGui.port = tmpPort;
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
        // disable端口号输入框
        this.portTextField.setEnabled(false);
        // 启动tcp服务器
//        MainGui.tcpServerThread = new TcpServerThread();
//        MainGui.tcpServerThread.start();

//        wjl code start
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//        wjl code end

        // disable启动tcp服务的按钮,enable停止tcp服务的按钮
        this.startTcpButton.setEnabled(false);
        this.stopTcpButton.setEnabled(true);
        this.tipsLabel.setText("提示：TCP服务运行中，正在等待连接...");

        Socket socket = null;
        try {
            socket = server.accept();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // 弹窗提示
        JOptionPane.showMessageDialog(this, "已成功启动TCP服务！");
        this.setVisible(false);
        CarGui.launch(socket);
    }

    /**
     * stopTcpButton点击事件
     * @param e     点击事件
     */
    private void stopTcpButtonActionPerformed(ActionEvent e) {
        // 关闭tcp服务器
        try {
            MainGui.tcpServerThread.serverSocket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            MainGui.tcpServerThread = null;
        }
        // disable启动tcp服务的按钮,enable停止tcp服务的按钮
        this.startTcpButton.setEnabled(true);
        this.stopTcpButton.setEnabled(false);
        this.tipsLabel.setText("提示：点击【启动TCP服务】按钮，启动服务器");
        // enable端口号输入框
        this.portTextField.setEnabled(true);
        // 弹窗提示
        JOptionPane.showMessageDialog(this, "已关闭TCP服务！");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        ipAddressLabel = new JLabel();
        macAddressLabel = new JLabel();
        tipsLabel = new JLabel();
        label6 = new JLabel();
        portTextField = new JTextField();
        startTcpButton = new JButton();
        stopTcpButton = new JButton();

        //======== this ========
        setTitle("\u79d1\u4e09\u9053\u8def\u8003\u8bd5\u7cfb\u7edf\u8f66\u8f86\u4f20\u611f\u5668\u6a21\u62df");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("ip\u5730\u5740\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(90, 30), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("mac\u5730\u5740\uff1a");
        contentPane.add(label2);
        label2.setBounds(90, 70, 70, 15);

        //---- ipAddressLabel ----
        ipAddressLabel.setText("text");
        contentPane.add(ipAddressLabel);
        ipAddressLabel.setBounds(165, 30, 150, ipAddressLabel.getPreferredSize().height);

        //---- macAddressLabel ----
        macAddressLabel.setText("text");
        contentPane.add(macAddressLabel);
        macAddressLabel.setBounds(165, 70, 150, 15);

        //---- tipsLabel ----
        tipsLabel.setText("\u63d0\u793a\uff1a\u70b9\u51fb\u3010\u542f\u52a8TCP\u670d\u52a1\u3011\u6309\u94ae\uff0c\u542f\u52a8\u670d\u52a1\u5668");
        contentPane.add(tipsLabel);
        tipsLabel.setBounds(45, 200, 285, 40);

        //---- label6 ----
        label6.setText("\u7aef\u53e3\u53f7\uff1a");
        contentPane.add(label6);
        label6.setBounds(90, 110, 55, label6.getPreferredSize().height);

        //---- portTextField ----
        portTextField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        portTextField.setText("10101");
        contentPane.add(portTextField);
        portTextField.setBounds(165, 110, 125, portTextField.getPreferredSize().height);

        //---- startTcpButton ----
        startTcpButton.setText("\u542f\u52a8TCP\u670d\u52a1");
        startTcpButton.addActionListener(e -> startTcpButtonActionPerformed(e));
        contentPane.add(startTcpButton);
        startTcpButton.setBounds(new Rectangle(new Point(60, 155), startTcpButton.getPreferredSize()));

        //---- stopTcpButton ----
        stopTcpButton.setText("\u5173\u95edTCP\u670d\u52a1");
        stopTcpButton.addActionListener(e -> stopTcpButtonActionPerformed(e));
        contentPane.add(stopTcpButton);
        stopTcpButton.setBounds(new Rectangle(new Point(195, 155), stopTcpButton.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(370, 285));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        // 设置窗体右上角关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 默认disable停止tcp服务的按钮
        this.stopTcpButton.setEnabled(false);
        // 获取ip、mac地址
        String ipAddress = LocalNetworkHelp.getLocalIp();
        if (ipAddress == null) {
            JOptionPane.showMessageDialog(this, "ip地址获取错误!");
        }
        String macAddress = LocalNetworkHelp.getMacAddressByIp(ipAddress);
        if (macAddress == null) {
            JOptionPane.showMessageDialog(this, "mac地址获取错误!");
        }
        this.ipAddressLabel.setText(ipAddress);
        this.macAddressLabel.setText(macAddress);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel ipAddressLabel;
    private JLabel macAddressLabel;
    private JLabel tipsLabel;
    private JLabel label6;
    private JTextField portTextField;
    private JButton startTcpButton;
    private JButton stopTcpButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        // 打开主界面
        MainGui mainGui = new MainGui();
        mainGui.setVisible(true);
    }
}
