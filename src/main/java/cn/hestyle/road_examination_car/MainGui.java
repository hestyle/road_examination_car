/*
 * Created by JFormDesigner on Fri Dec 11 11:48:06 CST 2020
 */

package cn.hestyle.road_examination_car;

import cn.hestyle.road_examination_car.util.LocalNetworkHelp;

import java.awt.*;
import javax.swing.*;

/**
 * @author hestyle
 */
public class MainGui extends JFrame {
    public MainGui() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        ipAddressLabel = new JLabel();
        macAddressLabel = new JLabel();
        label5 = new JLabel();

        //======== this ========
        setTitle("\u79d1\u4e09\u9053\u8def\u8003\u8bd5\u7cfb\u7edf\u8f66\u8f86\u4f20\u611f\u5668\u6a21\u62df");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("ip\u5730\u5740\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(90, 55), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("mac\u5730\u5740\uff1a");
        contentPane.add(label2);
        label2.setBounds(90, 105, 70, 15);

        //---- ipAddressLabel ----
        ipAddressLabel.setText("text");
        contentPane.add(ipAddressLabel);
        ipAddressLabel.setBounds(165, 55, 150, ipAddressLabel.getPreferredSize().height);

        //---- macAddressLabel ----
        macAddressLabel.setText("text");
        contentPane.add(macAddressLabel);
        macAddressLabel.setBounds(165, 105, 150, 15);

        //---- label5 ----
        label5.setText("\u6b63\u5728\u7b49\u5f85\u8fde\u63a5..");
        contentPane.add(label5);
        label5.setBounds(130, 170, 130, label5.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(370, 275));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        // 设置窗体右上角关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        MainGui mainGui = new MainGui();
        mainGui.setVisible(true);
    }
}
