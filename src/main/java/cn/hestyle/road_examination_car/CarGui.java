/*
 * Created by JFormDesigner on Sat Dec 12 16:30:42 CST 2020
 */

package cn.hestyle.road_examination_car;

import cn.hestyle.road_examination_car.task.SingleOperationMessageTask;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static cn.hestyle.road_examination_car.GuiApp.messageQueue;

/**
 * @author hestyle
 */
public class CarGui extends JFrame {
    public CarGui() {
        initComponents();
    }

    private void radioButton_clutchPedalOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(radioButton_clutchPedalOn.isEnabled()){
            messageQueue.putMessage(new SingleOperationMessageTask<String>("踩离合踏板"));
            radioButton_clutchPedalOn.setEnabled(false);
            radioButton_clutchPedalOff.setEnabled(true);
        }
    }

    private void radioButton_clutchPedalOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(radioButton_clutchPedalOff.isEnabled()){
            messageQueue.putMessage(new SingleOperationMessageTask<String>("松开开合踏板"));
            radioButton_clutchPedalOn.setEnabled(true);
            radioButton_clutchPedalOff.setEnabled(false);
        }
}

    private void radioButton_brakePedalOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(radioButton_brakePedalOn.isEnabled()){
            messageQueue.putMessage(new SingleOperationMessageTask<String>("踩刹车踏板"));
            radioButton_brakePedalOn.setEnabled(false);
            radioButton_brakePedalOff.setEnabled(true);
        }
    }

    private void radioButton_brakePedalOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(radioButton_brakePedalOff.isEnabled()){
            messageQueue.putMessage(new SingleOperationMessageTask<String>("松开刹车踏板"));
            radioButton_brakePedalOn.setEnabled(true);
            radioButton_brakePedalOff.setEnabled(false);
        }
    }

    private void radioButton_acceleratorPedalLightOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(radioButton_acceleratorPedalLightOn.isEnabled()){
            messageQueue.putMessage(new SingleOperationMessageTask<String>("轻踩加速踏板"));
            radioButton_acceleratorPedalLightOn.setEnabled(false);
            radioButton_acceleratorPedalOff.setEnabled(true);
        }
    }

    private void radioButton_acceleratorPedalOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(radioButton_acceleratorPedalOff.isEnabled()){
            messageQueue.putMessage(new SingleOperationMessageTask<String>("松开加速踏板"));
            radioButton_acceleratorPedalLightOn.setEnabled(true);
            radioButton_acceleratorPedalOff.setEnabled(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lightPanel = new JPanel();
        radioButton_lightDippedOn = new JRadioButton();
        radioButton_lightHighOn = new JRadioButton();
        radioButton_lightHightDippedOn = new JRadioButton();
        radioButton_lightTurnLeftSignalOn = new JRadioButton();
        radioButton_lightTurnRightSignalOn = new JRadioButton();
        radioButton_lightFogOn = new JRadioButton();
        radioButton_lightOutLineMarkOn = new JRadioButton();
        radioButton_lightHazardWarnOn = new JRadioButton();
        button1 = new JButton();
        gearPanel = new JPanel();
        radioButton9 = new JRadioButton();
        radioButton10 = new JRadioButton();
        radioButton11 = new JRadioButton();
        radioButton12 = new JRadioButton();
        radioButton13 = new JRadioButton();
        radioButton14 = new JRadioButton();
        radioButton15 = new JRadioButton();
        pedalPanel = new JPanel();
        clutchPedalPanel = new JPanel();
        radioButton_clutchPedalOn = new JRadioButton();
        radioButton_clutchPedalOff = new JRadioButton();
        brakePedalPanel = new JPanel();
        radioButton_brakePedalOn = new JRadioButton();
        radioButton_brakePedalOff = new JRadioButton();
        acceleratorPedalPanel = new JPanel();
        radioButton_acceleratorPedalLightOn = new JRadioButton();
        radioButton_acceleratorPedalOff = new JRadioButton();
        steerWheelPanel = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        parkBrakePanel = new JPanel();
        radioButton22 = new JRadioButton();
        radioButton23 = new JRadioButton();
        otherPanel = new JPanel();
        radioButton24 = new JRadioButton();
        label1 = new JLabel();
        speedLabel = new JLabel();

        //======== this ========
        setTitle("\u8f66\u8f86\u72b6\u6001\u6a21\u62df");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== lightPanel ========
        {
            lightPanel.setName("\u706f\u5149");
            lightPanel.setLayout(null);

            //---- radioButton_lightDippedOn ----
            radioButton_lightDippedOn.setText("\u8fd1\u5149\u706f");
            lightPanel.add(radioButton_lightDippedOn);
            radioButton_lightDippedOn.setBounds(new Rectangle(new Point(15, 65), radioButton_lightDippedOn.getPreferredSize()));

            //---- radioButton_lightHighOn ----
            radioButton_lightHighOn.setText("\u8fdc\u5149\u706f");
            lightPanel.add(radioButton_lightHighOn);
            radioButton_lightHighOn.setBounds(new Rectangle(new Point(95, 65), radioButton_lightHighOn.getPreferredSize()));

            //---- radioButton_lightHightDippedOn ----
            radioButton_lightHightDippedOn.setText("\u8fdc\u3001\u8fd1\u706f\u5149\u4ea4\u66ff\u95ea\u70c1");
            lightPanel.add(radioButton_lightHightDippedOn);
            radioButton_lightHightDippedOn.setBounds(new Rectangle(new Point(165, 65), radioButton_lightHightDippedOn.getPreferredSize()));

            //---- radioButton_lightTurnLeftSignalOn ----
            radioButton_lightTurnLeftSignalOn.setText("\u5de6\u8f6c\u706f");
            lightPanel.add(radioButton_lightTurnLeftSignalOn);
            radioButton_lightTurnLeftSignalOn.setBounds(new Rectangle(new Point(15, 15), radioButton_lightTurnLeftSignalOn.getPreferredSize()));

            //---- radioButton_lightTurnRightSignalOn ----
            radioButton_lightTurnRightSignalOn.setText("\u53f3\u8f6c\u706f");
            lightPanel.add(radioButton_lightTurnRightSignalOn);
            radioButton_lightTurnRightSignalOn.setBounds(new Rectangle(new Point(95, 15), radioButton_lightTurnRightSignalOn.getPreferredSize()));

            //---- radioButton_lightFogOn ----
            radioButton_lightFogOn.setText("\u96fe\u706f");
            lightPanel.add(radioButton_lightFogOn);
            radioButton_lightFogOn.setBounds(new Rectangle(new Point(15, 40), radioButton_lightFogOn.getPreferredSize()));

            //---- radioButton_lightOutLineMarkOn ----
            radioButton_lightOutLineMarkOn.setText("\u793a\u5eca\u706f");
            lightPanel.add(radioButton_lightOutLineMarkOn);
            radioButton_lightOutLineMarkOn.setBounds(new Rectangle(new Point(95, 40), radioButton_lightOutLineMarkOn.getPreferredSize()));

            //---- radioButton_lightHazardWarnOn ----
            radioButton_lightHazardWarnOn.setText("\u5371\u9669\u8b66\u62a5\u706f");
            lightPanel.add(radioButton_lightHazardWarnOn);
            radioButton_lightHazardWarnOn.setBounds(new Rectangle(new Point(165, 40), radioButton_lightHazardWarnOn.getPreferredSize()));

            //---- button1 ----
            button1.setText("\u5173\u95ed\u6240\u6709\u706f\u5149");
            lightPanel.add(button1);
            button1.setBounds(new Rectangle(new Point(315, 35), button1.getPreferredSize()));
        }
        contentPane.add(lightPanel);
        lightPanel.setBounds(310, 65, 435, 95);

        //======== gearPanel ========
        {
            gearPanel.setLayout(null);

            //---- radioButton9 ----
            radioButton9.setText("1\uff08\u524d\u8fdb\uff09\u6321");
            gearPanel.add(radioButton9);
            radioButton9.setBounds(new Rectangle(new Point(10, 10), radioButton9.getPreferredSize()));

            //---- radioButton10 ----
            radioButton10.setText("2\u6863");
            gearPanel.add(radioButton10);
            radioButton10.setBounds(new Rectangle(new Point(10, 90), radioButton10.getPreferredSize()));

            //---- radioButton11 ----
            radioButton11.setText("3\u6863");
            gearPanel.add(radioButton11);
            radioButton11.setBounds(new Rectangle(new Point(115, 10), radioButton11.getPreferredSize()));

            //---- radioButton12 ----
            radioButton12.setText("4\u6863");
            gearPanel.add(radioButton12);
            radioButton12.setBounds(new Rectangle(new Point(115, 90), radioButton12.getPreferredSize()));

            //---- radioButton13 ----
            radioButton13.setText("5\u6863");
            gearPanel.add(radioButton13);
            radioButton13.setBounds(new Rectangle(new Point(190, 10), radioButton13.getPreferredSize()));

            //---- radioButton14 ----
            radioButton14.setText("-1\uff08\u5012\uff09\u6863");
            gearPanel.add(radioButton14);
            radioButton14.setBounds(new Rectangle(new Point(190, 90), radioButton14.getPreferredSize()));

            //---- radioButton15 ----
            radioButton15.setText("\u7a7a\u6321");
            gearPanel.add(radioButton15);
            radioButton15.setBounds(new Rectangle(new Point(115, 50), radioButton15.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < gearPanel.getComponentCount(); i++) {
                    Rectangle bounds = gearPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = gearPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                gearPanel.setMinimumSize(preferredSize);
                gearPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(gearPanel);
        gearPanel.setBounds(245, 270, 285, 125);

        //======== pedalPanel ========
        {
            pedalPanel.setLayout(null);

            //======== clutchPedalPanel ========
            {
                clutchPedalPanel.setLayout(null);

                //---- radioButton_clutchPedalOn ----
                radioButton_clutchPedalOn.setText("\u8e29\u4f4f");
                radioButton_clutchPedalOn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        radioButton_clutchPedalOnMouseClicked(e);
                    }
                });
                clutchPedalPanel.add(radioButton_clutchPedalOn);
                radioButton_clutchPedalOn.setBounds(new Rectangle(new Point(10, 40), radioButton_clutchPedalOn.getPreferredSize()));

                //---- radioButton_clutchPedalOff ----
                radioButton_clutchPedalOff.setText("\u677e\u5f00");
                radioButton_clutchPedalOff.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        radioButton_clutchPedalOffMouseClicked(e);
                    }
                });
                clutchPedalPanel.add(radioButton_clutchPedalOff);
                radioButton_clutchPedalOff.setBounds(new Rectangle(new Point(10, 70), radioButton_clutchPedalOff.getPreferredSize()));
            }
            pedalPanel.add(clutchPedalPanel);
            clutchPedalPanel.setBounds(10, 35, 95, 100);

            //======== brakePedalPanel ========
            {
                brakePedalPanel.setLayout(null);

                //---- radioButton_brakePedalOn ----
                radioButton_brakePedalOn.setText("\u8e29\u4f4f");
                radioButton_brakePedalOn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        radioButton_brakePedalOnMouseClicked(e);
                    }
                });
                brakePedalPanel.add(radioButton_brakePedalOn);
                radioButton_brakePedalOn.setBounds(new Rectangle(new Point(10, 40), radioButton_brakePedalOn.getPreferredSize()));

                //---- radioButton_brakePedalOff ----
                radioButton_brakePedalOff.setText("\u677e\u5f00");
                radioButton_brakePedalOff.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        radioButton_brakePedalOffMouseClicked(e);
                    }
                });
                brakePedalPanel.add(radioButton_brakePedalOff);
                radioButton_brakePedalOff.setBounds(new Rectangle(new Point(10, 70), radioButton_brakePedalOff.getPreferredSize()));
            }
            pedalPanel.add(brakePedalPanel);
            brakePedalPanel.setBounds(110, 35, 95, 100);

            //======== acceleratorPedalPanel ========
            {
                acceleratorPedalPanel.setLayout(null);

                //---- radioButton_acceleratorPedalLightOn ----
                radioButton_acceleratorPedalLightOn.setText("\u8f7b\u8e29");
                radioButton_acceleratorPedalLightOn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        radioButton_acceleratorPedalLightOnMouseClicked(e);
                    }
                });
                acceleratorPedalPanel.add(radioButton_acceleratorPedalLightOn);
                radioButton_acceleratorPedalLightOn.setBounds(new Rectangle(new Point(10, 40), radioButton_acceleratorPedalLightOn.getPreferredSize()));

                //---- radioButton_acceleratorPedalOff ----
                radioButton_acceleratorPedalOff.setText("\u677e\u5f00");
                radioButton_acceleratorPedalOff.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        radioButton_acceleratorPedalOffMouseClicked(e);
                    }
                });
                acceleratorPedalPanel.add(radioButton_acceleratorPedalOff);
                radioButton_acceleratorPedalOff.setBounds(new Rectangle(new Point(10, 70), radioButton_acceleratorPedalOff.getPreferredSize()));
            }
            pedalPanel.add(acceleratorPedalPanel);
            acceleratorPedalPanel.setBounds(210, 35, 95, 100);
        }
        contentPane.add(pedalPanel);
        pedalPanel.setBounds(5, 20, 310, 140);

        //======== steerWheelPanel ========
        {
            steerWheelPanel.setLayout(null);

            //---- button2 ----
            button2.setText("\u5de6\u8f6c\u7ea6\u534a\u5708(0\u00b0~15\u00b0)");
            steerWheelPanel.add(button2);
            button2.setBounds(new Rectangle(new Point(175, 15), button2.getPreferredSize()));

            //---- button3 ----
            button3.setText("\u5de6\u8f6c\u7ea6\u4e00\u5708(15\u00b0~45\u00b0)");
            steerWheelPanel.add(button3);
            button3.setBounds(new Rectangle(new Point(10, 15), button3.getPreferredSize()));

            //---- button4 ----
            button4.setText("\u53f3\u8f6c\u7ea6\u534a\u5708(0\u00b0~15\u00b0)");
            steerWheelPanel.add(button4);
            button4.setBounds(new Rectangle(new Point(360, 15), button4.getPreferredSize()));

            //---- button5 ----
            button5.setText("\u53f3\u8f6c\u7ea6\u4e00\u5708(15\u00b0~45\u00b0)");
            steerWheelPanel.add(button5);
            button5.setBounds(new Rectangle(new Point(520, 15), button5.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < steerWheelPanel.getComponentCount(); i++) {
                    Rectangle bounds = steerWheelPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = steerWheelPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                steerWheelPanel.setMinimumSize(preferredSize);
                steerWheelPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(steerWheelPanel);
        steerWheelPanel.setBounds(45, 185, 690, 60);

        //======== parkBrakePanel ========
        {
            parkBrakePanel.setLayout(null);

            //---- radioButton22 ----
            radioButton22.setText("\u62c9\u8d77");
            parkBrakePanel.add(radioButton22);
            radioButton22.setBounds(new Rectangle(new Point(20, 15), radioButton22.getPreferredSize()));

            //---- radioButton23 ----
            radioButton23.setText("\u653e\u4e0b");
            parkBrakePanel.add(radioButton23);
            radioButton23.setBounds(new Rectangle(new Point(20, 55), radioButton23.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < parkBrakePanel.getComponentCount(); i++) {
                    Rectangle bounds = parkBrakePanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = parkBrakePanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                parkBrakePanel.setMinimumSize(preferredSize);
                parkBrakePanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(parkBrakePanel);
        parkBrakePanel.setBounds(590, 285, 90, 95);

        //======== otherPanel ========
        {
            otherPanel.setLayout(null);

            //---- radioButton24 ----
            radioButton24.setText("\u89c2\u5bdf\u540e\u89c6\u955c");
            otherPanel.add(radioButton24);
            radioButton24.setBounds(new Rectangle(new Point(10, 15), radioButton24.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < otherPanel.getComponentCount(); i++) {
                    Rectangle bounds = otherPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = otherPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                otherPanel.setMinimumSize(preferredSize);
                otherPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(otherPanel);
        otherPanel.setBounds(80, 285, 105, 95);

        //---- label1 ----
        label1.setText("\u8f66\u901f\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(345, 35), label1.getPreferredSize()));

        //---- speedLabel ----
        speedLabel.setText("0km/h");
        contentPane.add(speedLabel);
        speedLabel.setBounds(new Rectangle(new Point(395, 35), speedLabel.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(780, 450));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        // 设置窗体右上角关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 离合器panel绘制边框
        clutchPedalPanel.setBorder(BorderFactory.createTitledBorder("离合器踏板"));
        // 离合器状态按钮组
        ButtonGroup buttonGroup_clutch = new ButtonGroup();
        buttonGroup_clutch.add(radioButton_clutchPedalOn);
        buttonGroup_clutch.add(radioButton_clutchPedalOff);

        // 刹车踏板panel绘制边框
        brakePedalPanel.setBorder(BorderFactory.createTitledBorder("刹车踏板"));
        // 刹车踏板状态按钮组
        ButtonGroup buttonGroup_brake = new ButtonGroup();
        buttonGroup_brake.add(radioButton_brakePedalOn);
        buttonGroup_brake.add(radioButton_brakePedalOff);

        // 加速踏板panel绘制边框
        acceleratorPedalPanel.setBorder(BorderFactory.createTitledBorder("加速踏板"));
        // 加速踏板状态按钮组
        ButtonGroup buttonGroup_step = new ButtonGroup();
        buttonGroup_step.add(radioButton_acceleratorPedalLightOn);
        buttonGroup_step.add(radioButton_acceleratorPedalOff);

        //左右转向灯组
        ButtonGroup buttonGroup_trunSignal = new ButtonGroup();
        buttonGroup_trunSignal.add(radioButton_lightTurnLeftSignalOn);
        buttonGroup_trunSignal.add(radioButton_lightTurnRightSignalOn);

        // 远近灯光、交替 按钮组
        ButtonGroup buttonGroup_highDipped = new ButtonGroup();
        buttonGroup_highDipped.add(radioButton_lightDippedOn);
        buttonGroup_highDipped.add(radioButton_lightHighOn);
        buttonGroup_highDipped.add(radioButton_lightHightDippedOn);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel lightPanel;
    private JRadioButton radioButton_lightDippedOn;
    private JRadioButton radioButton_lightHighOn;
    private JRadioButton radioButton_lightHightDippedOn;
    private JRadioButton radioButton_lightTurnLeftSignalOn;
    private JRadioButton radioButton_lightTurnRightSignalOn;
    private JRadioButton radioButton_lightFogOn;
    private JRadioButton radioButton_lightOutLineMarkOn;
    private JRadioButton radioButton_lightHazardWarnOn;
    private JButton button1;
    private JPanel gearPanel;
    private JRadioButton radioButton9;
    private JRadioButton radioButton10;
    private JRadioButton radioButton11;
    private JRadioButton radioButton12;
    private JRadioButton radioButton13;
    private JRadioButton radioButton14;
    private JRadioButton radioButton15;
    private JPanel pedalPanel;
    private JPanel clutchPedalPanel;
    private JRadioButton radioButton_clutchPedalOn;
    private JRadioButton radioButton_clutchPedalOff;
    private JPanel brakePedalPanel;
    private JRadioButton radioButton_brakePedalOn;
    private JRadioButton radioButton_brakePedalOff;
    private JPanel acceleratorPedalPanel;
    private JRadioButton radioButton_acceleratorPedalLightOn;
    private JRadioButton radioButton_acceleratorPedalOff;
    private JPanel steerWheelPanel;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel parkBrakePanel;
    private JRadioButton radioButton22;
    private JRadioButton radioButton23;
    private JPanel otherPanel;
    private JRadioButton radioButton24;
    private JLabel label1;
    private JLabel speedLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
