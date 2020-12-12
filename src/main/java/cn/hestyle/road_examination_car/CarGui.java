/*
 * Created by JFormDesigner on Sat Dec 12 16:30:42 CST 2020
 */

package cn.hestyle.road_examination_car;

import java.awt.*;
import javax.swing.*;

/**
 * @author hestyle
 */
public class CarGui extends JFrame {
    public CarGui() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lightPanel = new JPanel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();
        radioButton5 = new JRadioButton();
        radioButton6 = new JRadioButton();
        radioButton7 = new JRadioButton();
        radioButton8 = new JRadioButton();
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
        radioButton16 = new JRadioButton();
        radioButton17 = new JRadioButton();
        brakePedalPanel = new JPanel();
        radioButton18 = new JRadioButton();
        radioButton19 = new JRadioButton();
        acceleratorPedalPanel = new JPanel();
        radioButton20 = new JRadioButton();
        radioButton21 = new JRadioButton();
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

            //---- radioButton1 ----
            radioButton1.setText("\u8fd1\u5149\u706f");
            lightPanel.add(radioButton1);
            radioButton1.setBounds(new Rectangle(new Point(15, 65), radioButton1.getPreferredSize()));

            //---- radioButton2 ----
            radioButton2.setText("\u8fdc\u5149\u706f");
            lightPanel.add(radioButton2);
            radioButton2.setBounds(new Rectangle(new Point(95, 65), radioButton2.getPreferredSize()));

            //---- radioButton3 ----
            radioButton3.setText("\u8fdc\u3001\u8fd1\u706f\u5149\u4ea4\u66ff\u95ea\u70c1");
            lightPanel.add(radioButton3);
            radioButton3.setBounds(new Rectangle(new Point(165, 65), radioButton3.getPreferredSize()));

            //---- radioButton4 ----
            radioButton4.setText("\u5de6\u8f6c\u706f");
            lightPanel.add(radioButton4);
            radioButton4.setBounds(new Rectangle(new Point(15, 15), radioButton4.getPreferredSize()));

            //---- radioButton5 ----
            radioButton5.setText("\u53f3\u8f6c\u706f");
            lightPanel.add(radioButton5);
            radioButton5.setBounds(new Rectangle(new Point(95, 15), radioButton5.getPreferredSize()));

            //---- radioButton6 ----
            radioButton6.setText("\u96fe\u706f");
            lightPanel.add(radioButton6);
            radioButton6.setBounds(new Rectangle(new Point(15, 40), radioButton6.getPreferredSize()));

            //---- radioButton7 ----
            radioButton7.setText("\u793a\u5eca\u706f");
            lightPanel.add(radioButton7);
            radioButton7.setBounds(new Rectangle(new Point(95, 40), radioButton7.getPreferredSize()));

            //---- radioButton8 ----
            radioButton8.setText("\u5371\u9669\u8b66\u62a5\u706f");
            lightPanel.add(radioButton8);
            radioButton8.setBounds(new Rectangle(new Point(165, 40), radioButton8.getPreferredSize()));

            //---- button1 ----
            button1.setText("\u5173\u95ed\u6240\u6709\u706f\u5149");
            lightPanel.add(button1);
            button1.setBounds(new Rectangle(new Point(315, 35), button1.getPreferredSize()));
        }
        contentPane.add(lightPanel);
        lightPanel.setBounds(300, 65, 435, 95);

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

            {
                // compute preferred size
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

                //---- radioButton16 ----
                radioButton16.setText("\u8e29\u4f4f");
                clutchPedalPanel.add(radioButton16);
                radioButton16.setBounds(new Rectangle(new Point(10, 10), radioButton16.getPreferredSize()));

                //---- radioButton17 ----
                radioButton17.setText("\u677e\u5f00");
                clutchPedalPanel.add(radioButton17);
                radioButton17.setBounds(new Rectangle(new Point(10, 45), radioButton17.getPreferredSize()));
            }
            pedalPanel.add(clutchPedalPanel);
            clutchPedalPanel.setBounds(10, 10, 70, 75);

            //======== brakePedalPanel ========
            {
                brakePedalPanel.setLayout(null);

                //---- radioButton18 ----
                radioButton18.setText("\u8e29\u4f4f");
                brakePedalPanel.add(radioButton18);
                radioButton18.setBounds(new Rectangle(new Point(10, 10), radioButton18.getPreferredSize()));

                //---- radioButton19 ----
                radioButton19.setText("\u677e\u5f00");
                brakePedalPanel.add(radioButton19);
                radioButton19.setBounds(new Rectangle(new Point(10, 45), radioButton19.getPreferredSize()));
            }
            pedalPanel.add(brakePedalPanel);
            brakePedalPanel.setBounds(80, 10, 70, 75);

            //======== acceleratorPedalPanel ========
            {
                acceleratorPedalPanel.setLayout(null);

                //---- radioButton20 ----
                radioButton20.setText("\u8f7b\u8e29");
                acceleratorPedalPanel.add(radioButton20);
                radioButton20.setBounds(new Rectangle(new Point(10, 10), radioButton20.getPreferredSize()));

                //---- radioButton21 ----
                radioButton21.setText("\u677e\u5f00");
                acceleratorPedalPanel.add(radioButton21);
                radioButton21.setBounds(new Rectangle(new Point(10, 45), radioButton21.getPreferredSize()));
            }
            pedalPanel.add(acceleratorPedalPanel);
            acceleratorPedalPanel.setBounds(150, 10, 70, 75);
        }
        contentPane.add(pedalPanel);
        pedalPanel.setBounds(45, 65, 230, 95);

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

            {
                // compute preferred size
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

            {
                // compute preferred size
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

            {
                // compute preferred size
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
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel lightPanel;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JRadioButton radioButton8;
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
    private JRadioButton radioButton16;
    private JRadioButton radioButton17;
    private JPanel brakePedalPanel;
    private JRadioButton radioButton18;
    private JRadioButton radioButton19;
    private JPanel acceleratorPedalPanel;
    private JRadioButton radioButton20;
    private JRadioButton radioButton21;
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
