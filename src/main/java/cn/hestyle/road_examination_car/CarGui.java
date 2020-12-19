/*
 * Created by JFormDesigner on Sat Dec 12 16:30:42 CST 2020
 */

package cn.hestyle.road_examination_car;

import cn.hestyle.road_examination_car.entity.Car;
import cn.hestyle.road_examination_car.entity.MessageTaskQueue;
import cn.hestyle.tcp.TcpRequestMessage;
import cn.hestyle.tcp.TcpResponseMessage;
import cn.hestyle.road_examination_car.woker.MessageSender;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * @author hestyle
 */
public class CarGui extends JFrame implements WindowListener {
    public CarGui(ServerSocket serverSocket, Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
        this.oos = oos;
        this.ois = ois;
        this.socket = socket;
        this.serverSocket = serverSocket;
        initComponents();
    }


    //踩离合
    private void radioButton_clutchPedalOnMouseClicked(MouseEvent e) {
        // TODO add your code here
//        if(!radioButton_clutchPedalOn.isSelected())
//            return;
        System.err.println("dianji 0");
        if (radioButton_clutchPedalOn.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("STEP_ON_CLUTCH_PEDAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_clutchPedalOn.setEnabled(false);
            radioButton_clutchPedalOff.setEnabled(true);
            buttonGroup_clutch.setSelected(radioButton_clutchPedalOn.getModel(), true);
        }
    }

    private void radioButton_clutchPedalOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        System.err.println("dianji 1");
        if (radioButton_clutchPedalOff.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("STEP_OFF_CLUTCH_PEDAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);


            radioButton_clutchPedalOn.setEnabled(true);
            radioButton_clutchPedalOff.setEnabled(false);
            buttonGroup_clutch.setSelected(radioButton_clutchPedalOff.getModel(), true);

            if(lastGear == 1 || lastGear == -1){
                Double speed = Double.valueOf(speedLabel.getText());
                if(speed == 0D){
                    speedLabel.setText("10");
                    odometer.setSpeed(10D);
                    odometer.wait = false;
                    Map<String, String> map = new HashMap<>();
                    map.put("SPEED", "10");
                    sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);
                }
            }
        }
    }

    // 踩刹车
    private void radioButton_brakePedalOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_brakePedalOn.isEnabled()) {
            if (radioButton_acceleratorPedalOff.isEnabled()) {
                List<String> temp = new LinkedList<>();
                temp.add("STEP_OFF_ACCELERATOR_PEDAL");
                sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

                radioButton_acceleratorPedalOn.setEnabled(true);
                radioButton_acceleratorPedalOff.setEnabled(false);
                buttonGroup_accelerator.setSelected(radioButton_acceleratorPedalOff.getModel(), true);
            }
            List<String> temp = new LinkedList<>();
            temp.add("STEP_ON_BRAKE_PEDAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_brakePedalOn.setEnabled(false);
            radioButton_brakePedalOff.setEnabled(true);
            buttonGroup_brake.setSelected(radioButton_brakePedalOn.getModel(), true);
            synchronized (brakeActionHandler) {
                brakeActionHandler.notify();
            }
        }
    }

    private void radioButton_brakePedalOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_brakePedalOff.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("STEP_OFF_BRAKE_PEDAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_brakePedalOn.setEnabled(true);
            radioButton_brakePedalOff.setEnabled(false);
            buttonGroup_brake.setSelected(radioButton_brakePedalOff.getModel(), true);
        }
    }

    // 踩加速踏板
    private void radioButton_acceleratorPedalOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_acceleratorPedalOn.isEnabled()) {
            if (radioButton_brakePedalOff.isEnabled()) {
                List<String> temp = new LinkedList<>();
                temp.add("STEP_OFF_BRAKE_PEDAL");
                sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

                radioButton_brakePedalOn.setEnabled(true);
                radioButton_brakePedalOff.setEnabled(false);
                buttonGroup_brake.setSelected(radioButton_brakePedalOff.getModel(), true);
            }
            List<String> temp = new LinkedList<>();
            temp.add("LIGHT_STEP_ON_ACCELERATOR_PEDAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_acceleratorPedalOn.setEnabled(false);
            radioButton_acceleratorPedalOff.setEnabled(true);
            buttonGroup_accelerator.setSelected(radioButton_acceleratorPedalOn.getModel(), true);
            synchronized (acceleratorActionHandler) {
                acceleratorActionHandler.notify();
            }
        }
    }

    // 松开加速踏板
    private void radioButton_acceleratorPedalOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_acceleratorPedalOff.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("STEP_OFF_ACCELERATOR_PEDAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_acceleratorPedalOn.setEnabled(true);
            radioButton_acceleratorPedalOff.setEnabled(false);
            buttonGroup_accelerator.setSelected(radioButton_acceleratorPedalOff.getModel(), true);

            if (acceleratorActionHandler.isWaittingGear() || acceleratorActionHandler.isWaittingParkBrakeOff()) {
                synchronized (acceleratorActionHandler) {
                    acceleratorActionHandler.notify();
                }
            }
        }
    }

    //近光灯
    private void radioButton_lightDippedOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightDippedOn.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_DIPPED_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_lightDippedOn.setEnabled(false);
            radioButton_lightHighDippedClose.setEnabled(true);
            radioButton_lightHighOn.setEnabled(true);
            radioButton_lightHighDippedOn.setEnabled(true);
        }
    }

    //远光灯
    private void radioButton_lightHighOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightHighOn.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_HIGH_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_lightHighOn.setEnabled(false);
            radioButton_lightHighDippedClose.setEnabled(true);
            radioButton_lightDippedOn.setEnabled(true);
            radioButton_lightHighDippedOn.setEnabled(true);
        }
    }

    // 远近交替
    private void radioButton_lightHighDippedOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightHighDippedOn.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_HIGH_DIPPED_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_lightHighDippedOn.setEnabled(false);
            radioButton_lightHighDippedClose.setEnabled(true);
            radioButton_lightHighOn.setEnabled(true);
            radioButton_lightDippedOn.setEnabled(true);
        }
    }

    // 关闭远近灯光
    private void radioButton_lightHighDippedCloseMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightHighDippedClose.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_OFF_ALL_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            buttonGroup_highDipped.clearSelection();
            radioButton_lightHighDippedClose.setEnabled(false);
            radioButton_lightHighDippedOn.setEnabled(true);
            radioButton_lightHighOn.setEnabled(true);
            radioButton_lightDippedOn.setEnabled(true);
        }
    }

    // 左转灯
    private void radioButton_lightTurnLeftSignalOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightTurnLeftSignalOn.isEnabled()) {
            exitCloseTurnRightSignalThread = true;

            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_LEFT_TURN_SIGNAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_lightTurnLeftSignalOn.setEnabled(false);
            radioButton_lightTurnRightSignalOn.setEnabled(true);
            label4.setText("(5秒后自动关闭)");

            exitCloseTurnLeftSignalThread = false;
            closeTurnLeftSignalThread = new CloseTurnLeftSignalThread();
            closeTurnLeftSignalThread.start();
        }
    }

    // 右转灯
    private void radioButton_lightTurnRightSignalOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightTurnRightSignalOn.isEnabled()) {
            exitCloseTurnLeftSignalThread = true;

            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_RIGHT_TURN_SIGNAL");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_lightTurnRightSignalOn.setEnabled(false);
            radioButton_lightTurnLeftSignalOn.setEnabled(true);
            label4.setText("(5秒后自动关闭)");

            exitCloseTurnRightSignalThread = false;
            CloseTurnRightSignalThread closeTurnRightSignalThread = new CloseTurnRightSignalThread();
            closeTurnRightSignalThread.start();
        }
    }

    // 关闭转向灯
//    private void radioButton_lightTurnSignalOffClicked(MouseEvent e) {
//        // TODO add your code here
//        if (radioButton_lightTurnSignalOff.isEnabled()) {
//            List<String> temp = new LinkedList<>();
//            temp.add("TURN_OFF_ALL_LIGHT");
//            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
//
//            radioButton_lightTurnSignalOff.setEnabled(false);
//            radioButton_lightTurnRightSignalOn.setEnabled(true);
//            radioButton_lightTurnLeftSignalOn.setEnabled(true);
//            buttonGroup_turnSignal.clearSelection();
//        }
//    }

    // 雾灯
    private void radioButton_lightFogOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightFogOn.isSelected()) {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_FOG_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
        } else {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_OFF_ALL_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
        }
    }

    // 示廓灯
    private void radioButton_lightOutLineMarkOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightOutLineMarkOn.isSelected()) {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_OUTLINE_MARK_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
        } else {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_OFF_ALL_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
        }
    }

    // 危险报警灯
    private void radioButton_lightHazardWarnOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_lightHazardWarnOn.isSelected()) {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_ON_HAZARD_WARN_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
        } else {
            List<String> temp = new LinkedList<>();
            temp.add("TURN_OFF_ALL_LIGHT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
        }
    }

    // 关闭所有灯光
    private void button_lightOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        buttonGroup_highDipped.clearSelection();
        buttonGroup_turnSignal.clearSelection();
//        radioButton_lightTurnSignalOff.setEnabled(false);
    }

    // 系安全带
    private void radioButton_safetyBeltOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_safetyBeltOn.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("FASTEN_SEAT_BELT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_BASE_STATE);

            radioButton_safetyBeltOn.setEnabled(false);
            radioButton_safetyBeltOff.setEnabled(true);
        }
    }

    // 解安全带
    private void radioButton_safetyBeltOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_safetyBeltOff.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("UNFASTEN_SEAT_BELT");
            sendMessage(temp, TcpResponseMessage.RESPONSE_BASE_STATE);

            radioButton_safetyBeltOn.setEnabled(true);
            radioButton_safetyBeltOff.setEnabled(false);
        }
    }

    // 关车门
    private void radioButton_doorCloseMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_doorClose.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("CLOSE_DOOR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_BASE_STATE);

            radioButton_doorClose.setEnabled(false);
            radioButton_doorOpen.setEnabled(true);
        }
    }

    // 开车门
    private void radioButton_doorOpenMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_doorOpen.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("OPEN_DOOR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_BASE_STATE);

            radioButton_doorClose.setEnabled(true);
            radioButton_doorOpen.setEnabled(false);
        }
    }

    //  拉手刹
    private void radioButton_parkBrakeOnMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_parkBrakeOn.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("PULL_UP_PARK_BRAKE");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_parkBrakeOn.setEnabled(false);
            radioButton_parkBrakeOff.setEnabled(true);
            synchronized (parkBrakeActionHandler) {
                parkBrakeActionHandler.notify();
            }
        }
    }

    // 放手刹
    private void radioButton_parkBrakeOffMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_parkBrakeOff.isEnabled()) {
            List<String> temp = new LinkedList<>();
            temp.add("PULL_DOWN_PARK_BRAKE");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_parkBrakeOn.setEnabled(true);
            radioButton_parkBrakeOff.setEnabled(false);
        }
    }

    private void button_steerWheelModerateTurnLeftMouseClicked(MouseEvent e) {
        // TODO add your code here
        List<String> temp = new LinkedList<>();
        temp.add("STEER_WHEEL_MODERATE_TURN_LEFT");
        sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
    }

    private void button_steerWheelSlightTurnLeftMouseClicked(MouseEvent e) {
        // TODO add your code here
        List<String> temp = new LinkedList<>();
        temp.add("STEER_WHEEL_SLIGHT_TURN_LEFT");
        sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
    }

    private void button_steerWheelSlightTurnRightMouseClicked(MouseEvent e) {
        // TODO add your code here
        List<String> temp = new LinkedList<>();
        temp.add("STEER_WHEEL_SLIGHT_TURN_RIGHT");
        sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
    }

    private void button_steerWheelModerateTurnRightMouseClicked(MouseEvent e) {
        // TODO add your code here
        List<String> temp = new LinkedList<>();
        temp.add("STEER_WHEEL_MODERATE_TURN_RIGHT");
        sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);
    }

    private void radioButton_obeserveReverseMirrorMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_obeserveReverseMirror.isSelected()) {
            List<String> temp = new LinkedList<>();
            temp.add("OBSERVE_REARVIEW_MIRROR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            radioButton_obeserveReverseMirror.setSelected(false);
        }
    }

    // 空挡
    private void radioButton_gearNeutralMouseClicked(MouseEvent e) {
        // TODO add your code here
        // 踩离合状态 radioButton_clutchPedalOn.isEnabled() == false
        if (radioButton_gearNeutral.isEnabled()) {
            nextGear = 0;
            nextGearButton = radioButton_gearNeutral;

            List<String> temp = new LinkedList<>();
            temp.add("SET_NEUTRAL_GEAR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            Map<String, String> map = new HashMap<>();
            map.put("SPEED",speedLabel.getText());
            sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

            checkGearShift();
        }
    }

    // 一挡
    private void radioButton_gearForwardMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_gearForward.isEnabled()) {
            nextGear = 1;
            nextGearButton = radioButton_gearForward;

            List<String> temp = new LinkedList<>();
            temp.add("SET_FORWARD_GEAR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            Map<String, String> map = new HashMap<>();
            map.put("SPEED",speedLabel.getText());
            sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

            checkGearShift();
        }
    }

    // 二挡
    private void radioButton_gearSecondMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_gearSecond.isEnabled()) {
            nextGear = 2;
            nextGearButton = radioButton_gearSecond;

            List<String> temp = new LinkedList<>();
            temp.add("SET_SECOND_GEAR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            Map<String, String> map = new HashMap<>();
            map.put("SPEED",speedLabel.getText());
            sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

            checkGearShift();
        }
    }

    // 三挡
    private void radioButton_gearThirdMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_gearThird.isEnabled()) {
            nextGear = 3;
            nextGearButton = radioButton_gearThird;

            List<String> temp = new LinkedList<>();
            temp.add("SET_THIRD_GEAR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            Map<String, String> map = new HashMap<>();
            map.put("SPEED",speedLabel.getText());
            sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

            checkGearShift();
        }
    }

    // 四挡
    private void radioButton_gearFourthMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_gearFourth.isEnabled()) {
            nextGear = 4;
            nextGearButton = radioButton_gearFourth;


            List<String> temp = new LinkedList<>();
            temp.add("SET_FOURTH_GEAR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            Map<String, String> map = new HashMap<>();
            map.put("SPEED",speedLabel.getText());
            sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

            checkGearShift();
        }
    }

    // 五挡
    private void radioButton_gearFifthMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_gearFifth.isEnabled()) {
            nextGear = 5;
            nextGearButton = radioButton_gearFifth;


            List<String> temp = new LinkedList<>();
            temp.add("SET_FIFTH_GEAR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            Map<String, String> map = new HashMap<>();
            map.put("SPEED",speedLabel.getText());
            sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

            checkGearShift();
        }
    }

    // 倒挡
    private void radioButton_gearReverseMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (radioButton_gearReverse.isEnabled()) {
            nextGear = -1;
            nextGearButton = radioButton_gearReverse;


            List<String> temp = new LinkedList<>();
            temp.add("SET_REVERSE_GEAR");
            sendMessage(temp, TcpResponseMessage.RESPONSE_OPERATION_NAME);

            Map<String, String> map = new HashMap<>();
            map.put("SPEED",speedLabel.getText());
            sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

            checkGearShift();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label3 = new JLabel();
        mileageLabel = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        lightPanel = new JPanel();
        radioButton_lightDippedOn = new JRadioButton();
        radioButton_lightHighOn = new JRadioButton();
        radioButton_lightHighDippedOn = new JRadioButton();
        radioButton_lightTurnLeftSignalOn = new JRadioButton();
        radioButton_lightTurnRightSignalOn = new JRadioButton();
        radioButton_lightFogOn = new JRadioButton();
        radioButton_lightOutLineMarkOn = new JRadioButton();
        radioButton_lightHazardWarnOn = new JRadioButton();
        radioButton_lightHighDippedClose = new JRadioButton();
        label4 = new JLabel();
        gearPanel = new JPanel();
        radioButton_gearForward = new JRadioButton();
        radioButton_gearSecond = new JRadioButton();
        radioButton_gearThird = new JRadioButton();
        radioButton_gearFourth = new JRadioButton();
        radioButton_gearFifth = new JRadioButton();
        radioButton_gearReverse = new JRadioButton();
        radioButton_gearNeutral = new JRadioButton();
        pedalPanel = new JPanel();
        clutchPedalPanel = new JPanel();
        radioButton_clutchPedalOn = new JRadioButton();
        radioButton_clutchPedalOff = new JRadioButton();
        brakePedalPanel = new JPanel();
        radioButton_brakePedalOn = new JRadioButton();
        radioButton_brakePedalOff = new JRadioButton();
        acceleratorPedalPanel = new JPanel();
        radioButton_acceleratorPedalOn = new JRadioButton();
        radioButton_acceleratorPedalOff = new JRadioButton();
        parkBrakePanel = new JPanel();
        radioButton_parkBrakeOn = new JRadioButton();
        radioButton_parkBrakeOff = new JRadioButton();
        otherPanel = new JPanel();
        radioButton_obeserveReverseMirror = new JRadioButton();
        label1 = new JLabel();
        speedLabel = new JLabel();
        steerWheelPanel = new JPanel();
        button_steerWheelSlightTurnLeft = new JButton();
        button_steerWheelModerateTurnLeft = new JButton();
        button_steerWheelSlightTurnRight = new JButton();
        button_steerWheelModerateTurnRight = new JButton();
        safetyBeltPanel = new JPanel();
        radioButton_safetyBeltOn = new JRadioButton();
        radioButton_safetyBeltOff = new JRadioButton();
        doorPanel = new JPanel();
        radioButton_doorOpen = new JRadioButton();
        radioButton_doorClose = new JRadioButton();

        //======== this ========
        setTitle("\u8f66\u8f86\u72b6\u6001\u6a21\u62df");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label3 ----
        label3.setText("\u91cc\u7a0b\uff1a");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(185, 22), label3.getPreferredSize()));

        //---- mileageLabel ----
        mileageLabel.setText("0");
        contentPane.add(mileageLabel);
        mileageLabel.setBounds(240, 22, 40, mileageLabel.getPreferredSize().height);

        //---- label2 ----
        label2.setText("km/h");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(445, 22), label2.getPreferredSize()));

        //---- label5 ----
        label5.setText("km");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(290, 22), label5.getPreferredSize()));

        //======== lightPanel ========
        {
            lightPanel.setName("\u706f\u5149");
            lightPanel.setLayout(null);

            //---- radioButton_lightDippedOn ----
            radioButton_lightDippedOn.setText("\u8fd1\u5149\u706f");
            radioButton_lightDippedOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightDippedOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightDippedOn);
            radioButton_lightDippedOn.setBounds(new Rectangle(new Point(15, 70), radioButton_lightDippedOn.getPreferredSize()));

            //---- radioButton_lightHighOn ----
            radioButton_lightHighOn.setText("\u8fdc\u5149\u706f");
            radioButton_lightHighOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightHighOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightHighOn);
            radioButton_lightHighOn.setBounds(new Rectangle(new Point(95, 70), radioButton_lightHighOn.getPreferredSize()));

            //---- radioButton_lightHighDippedOn ----
            radioButton_lightHighDippedOn.setText("\u8fdc\u3001\u8fd1\u706f\u5149\u4ea4\u66ff\u95ea\u70c1");
            radioButton_lightHighDippedOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightHighDippedOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightHighDippedOn);
            radioButton_lightHighDippedOn.setBounds(new Rectangle(new Point(165, 70), radioButton_lightHighDippedOn.getPreferredSize()));

            //---- radioButton_lightTurnLeftSignalOn ----
            radioButton_lightTurnLeftSignalOn.setText("\u5de6\u8f6c\u706f");
            radioButton_lightTurnLeftSignalOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightTurnLeftSignalOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightTurnLeftSignalOn);
            radioButton_lightTurnLeftSignalOn.setBounds(new Rectangle(new Point(15, 20), radioButton_lightTurnLeftSignalOn.getPreferredSize()));

            //---- radioButton_lightTurnRightSignalOn ----
            radioButton_lightTurnRightSignalOn.setText("\u53f3\u8f6c\u706f");
            radioButton_lightTurnRightSignalOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightTurnRightSignalOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightTurnRightSignalOn);
            radioButton_lightTurnRightSignalOn.setBounds(new Rectangle(new Point(95, 20), radioButton_lightTurnRightSignalOn.getPreferredSize()));

            //---- radioButton_lightFogOn ----
            radioButton_lightFogOn.setText("\u96fe\u706f");
            radioButton_lightFogOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightFogOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightFogOn);
            radioButton_lightFogOn.setBounds(new Rectangle(new Point(15, 45), radioButton_lightFogOn.getPreferredSize()));

            //---- radioButton_lightOutLineMarkOn ----
            radioButton_lightOutLineMarkOn.setText("\u793a\u5eca\u706f");
            radioButton_lightOutLineMarkOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightOutLineMarkOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightOutLineMarkOn);
            radioButton_lightOutLineMarkOn.setBounds(new Rectangle(new Point(95, 45), radioButton_lightOutLineMarkOn.getPreferredSize()));

            //---- radioButton_lightHazardWarnOn ----
            radioButton_lightHazardWarnOn.setText("\u5371\u9669\u8b66\u62a5\u706f");
            radioButton_lightHazardWarnOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightHazardWarnOnMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightHazardWarnOn);
            radioButton_lightHazardWarnOn.setBounds(new Rectangle(new Point(165, 45), radioButton_lightHazardWarnOn.getPreferredSize()));

            //---- radioButton_lightHighDippedClose ----
            radioButton_lightHighDippedClose.setText("\u5173\u95ed");
            radioButton_lightHighDippedClose.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_lightHighDippedCloseMouseClicked(e);
                }
            });
            lightPanel.add(radioButton_lightHighDippedClose);
            radioButton_lightHighDippedClose.setBounds(new Rectangle(new Point(308, 70), radioButton_lightHighDippedClose.getPreferredSize()));
            lightPanel.add(label4);
            label4.setBounds(165, 20, 100, 25);
        }
        contentPane.add(lightPanel);
        lightPanel.setBounds(310, 55, 435, 100);

        //======== gearPanel ========
        {
            gearPanel.setLayout(null);

            //---- radioButton_gearForward ----
            radioButton_gearForward.setText("1\uff08\u524d\u8fdb\uff09\u6321");
            radioButton_gearForward.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_gearForwardMouseClicked(e);
                }
            });
            gearPanel.add(radioButton_gearForward);
            radioButton_gearForward.setBounds(new Rectangle(new Point(10, 37), radioButton_gearForward.getPreferredSize()));

            //---- radioButton_gearSecond ----
            radioButton_gearSecond.setText("2\u6863");
            radioButton_gearSecond.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_gearSecondMouseClicked(e);
                }
            });
            gearPanel.add(radioButton_gearSecond);
            radioButton_gearSecond.setBounds(new Rectangle(new Point(10, 117), radioButton_gearSecond.getPreferredSize()));

            //---- radioButton_gearThird ----
            radioButton_gearThird.setText("3\u6863");
            radioButton_gearThird.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_gearThirdMouseClicked(e);
                }
            });
            gearPanel.add(radioButton_gearThird);
            radioButton_gearThird.setBounds(new Rectangle(new Point(115, 37), radioButton_gearThird.getPreferredSize()));

            //---- radioButton_gearFourth ----
            radioButton_gearFourth.setText("4\u6863");
            radioButton_gearFourth.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_gearFourthMouseClicked(e);
                }
            });
            gearPanel.add(radioButton_gearFourth);
            radioButton_gearFourth.setBounds(new Rectangle(new Point(115, 117), radioButton_gearFourth.getPreferredSize()));

            //---- radioButton_gearFifth ----
            radioButton_gearFifth.setText("5\u6863");
            radioButton_gearFifth.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_gearFifthMouseClicked(e);
                }
            });
            gearPanel.add(radioButton_gearFifth);
            radioButton_gearFifth.setBounds(new Rectangle(new Point(190, 37), radioButton_gearFifth.getPreferredSize()));

            //---- radioButton_gearReverse ----
            radioButton_gearReverse.setText("-1\uff08\u5012\uff09\u6863");
            radioButton_gearReverse.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_gearReverseMouseClicked(e);
                }
            });
            gearPanel.add(radioButton_gearReverse);
            radioButton_gearReverse.setBounds(new Rectangle(new Point(190, 117), radioButton_gearReverse.getPreferredSize()));

            //---- radioButton_gearNeutral ----
            radioButton_gearNeutral.setText("\u7a7a\u6321");
            radioButton_gearNeutral.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_gearNeutralMouseClicked(e);
                }
            });
            gearPanel.add(radioButton_gearNeutral);
            radioButton_gearNeutral.setBounds(new Rectangle(new Point(115, 77), radioButton_gearNeutral.getPreferredSize()));

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
        gearPanel.setBounds(185, 285, 285, 145);

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
                radioButton_clutchPedalOn.setBounds(new Rectangle(new Point(10, 45), radioButton_clutchPedalOn.getPreferredSize()));

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
            clutchPedalPanel.setBounds(10, 40, 95, 100);

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
                radioButton_brakePedalOn.setBounds(new Rectangle(new Point(10, 45), radioButton_brakePedalOn.getPreferredSize()));

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
            brakePedalPanel.setBounds(110, 40, 95, 100);

            //======== acceleratorPedalPanel ========
            {
                acceleratorPedalPanel.setLayout(null);

                //---- radioButton_acceleratorPedalOn ----
                radioButton_acceleratorPedalOn.setText("\u8f7b\u8e29");
                radioButton_acceleratorPedalOn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        radioButton_acceleratorPedalOnMouseClicked(e);
                    }
                });
                acceleratorPedalPanel.add(radioButton_acceleratorPedalOn);
                radioButton_acceleratorPedalOn.setBounds(new Rectangle(new Point(10, 45), radioButton_acceleratorPedalOn.getPreferredSize()));

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
            acceleratorPedalPanel.setBounds(210, 40, 95, 100);
        }
        contentPane.add(pedalPanel);
        pedalPanel.setBounds(5, 15, 310, 140);

        //======== parkBrakePanel ========
        {
            parkBrakePanel.setLayout(null);

            //---- radioButton_parkBrakeOn ----
            radioButton_parkBrakeOn.setText("\u62c9\u8d77");
            radioButton_parkBrakeOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_parkBrakeOnMouseClicked(e);
                }
            });
            parkBrakePanel.add(radioButton_parkBrakeOn);
            radioButton_parkBrakeOn.setBounds(new Rectangle(new Point(20, 15), radioButton_parkBrakeOn.getPreferredSize()));

            //---- radioButton_parkBrakeOff ----
            radioButton_parkBrakeOff.setText("\u653e\u4e0b");
            radioButton_parkBrakeOff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_parkBrakeOffMouseClicked(e);
                }
            });
            parkBrakePanel.add(radioButton_parkBrakeOff);
            radioButton_parkBrakeOff.setBounds(new Rectangle(new Point(20, 55), radioButton_parkBrakeOff.getPreferredSize()));

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
        parkBrakePanel.setBounds(490, 335, 90, 95);

        //======== otherPanel ========
        {
            otherPanel.setLayout(null);

            //---- radioButton_obeserveReverseMirror ----
            radioButton_obeserveReverseMirror.setText("\u89c2\u5bdf\u540e\u89c6\u955c");
            radioButton_obeserveReverseMirror.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_obeserveReverseMirrorMouseClicked(e);
                }
            });
            otherPanel.add(radioButton_obeserveReverseMirror);
            radioButton_obeserveReverseMirror.setBounds(new Rectangle(new Point(10, 25), radioButton_obeserveReverseMirror.getPreferredSize()));

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
        otherPanel.setBounds(490, 255, 130, 55);

        //---- label1 ----
        label1.setText("\u8f66\u901f\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(345, 22), label1.getPreferredSize()));

        //---- speedLabel ----
        speedLabel.setText("0");
        contentPane.add(speedLabel);
        speedLabel.setBounds(395, 22, 40, speedLabel.getPreferredSize().height);

        //======== steerWheelPanel ========
        {
            steerWheelPanel.setLayout(null);

            //---- button_steerWheelSlightTurnLeft ----
            button_steerWheelSlightTurnLeft.setText("\u5de6\u8f6c\u7ea6\u534a\u5708(0\u00b0~15\u00b0)");
            button_steerWheelSlightTurnLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button_steerWheelSlightTurnLeftMouseClicked(e);
                }
            });
            steerWheelPanel.add(button_steerWheelSlightTurnLeft);
            button_steerWheelSlightTurnLeft.setBounds(new Rectangle(new Point(175, 27), button_steerWheelSlightTurnLeft.getPreferredSize()));

            //---- button_steerWheelModerateTurnLeft ----
            button_steerWheelModerateTurnLeft.setText("\u5de6\u8f6c\u7ea6\u4e00\u5708(15\u00b0~45\u00b0)");
            button_steerWheelModerateTurnLeft.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button_steerWheelModerateTurnLeftMouseClicked(e);
                }
            });
            steerWheelPanel.add(button_steerWheelModerateTurnLeft);
            button_steerWheelModerateTurnLeft.setBounds(new Rectangle(new Point(10, 27), button_steerWheelModerateTurnLeft.getPreferredSize()));

            //---- button_steerWheelSlightTurnRight ----
            button_steerWheelSlightTurnRight.setText("\u53f3\u8f6c\u7ea6\u534a\u5708(0\u00b0~15\u00b0)");
            button_steerWheelSlightTurnRight.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button_steerWheelSlightTurnRightMouseClicked(e);
                }
            });
            steerWheelPanel.add(button_steerWheelSlightTurnRight);
            button_steerWheelSlightTurnRight.setBounds(new Rectangle(new Point(360, 27), button_steerWheelSlightTurnRight.getPreferredSize()));

            //---- button_steerWheelModerateTurnRight ----
            button_steerWheelModerateTurnRight.setText("\u53f3\u8f6c\u7ea6\u4e00\u5708(15\u00b0~45\u00b0)");
            button_steerWheelModerateTurnRight.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button_steerWheelModerateTurnRightMouseClicked(e);
                }
            });
            steerWheelPanel.add(button_steerWheelModerateTurnRight);
            button_steerWheelModerateTurnRight.setBounds(new Rectangle(new Point(520, 27), button_steerWheelModerateTurnRight.getPreferredSize()));

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
        steerWheelPanel.setBounds(10, 165, 735, 60);

        //======== safetyBeltPanel ========
        {
            safetyBeltPanel.setLayout(null);

            //---- radioButton_safetyBeltOn ----
            radioButton_safetyBeltOn.setText("\u7cfb\u5b89\u5168\u5e26");
            radioButton_safetyBeltOn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_safetyBeltOnMouseClicked(e);
                }
            });
            safetyBeltPanel.add(radioButton_safetyBeltOn);
            radioButton_safetyBeltOn.setBounds(10, 19, 105, radioButton_safetyBeltOn.getPreferredSize().height);

            //---- radioButton_safetyBeltOff ----
            radioButton_safetyBeltOff.setText("\u89e3\u5b89\u5168\u5e26");
            radioButton_safetyBeltOff.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_safetyBeltOffMouseClicked(e);
                }
            });
            safetyBeltPanel.add(radioButton_safetyBeltOff);
            radioButton_safetyBeltOff.setBounds(10, 48, 105, 19);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < safetyBeltPanel.getComponentCount(); i++) {
                    Rectangle bounds = safetyBeltPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = safetyBeltPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                safetyBeltPanel.setMinimumSize(preferredSize);
                safetyBeltPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(safetyBeltPanel);
        safetyBeltPanel.setBounds(10, 275, 145, 75);

        //======== doorPanel ========
        {
            doorPanel.setLayout(null);

            //---- radioButton_doorOpen ----
            radioButton_doorOpen.setText("\u6253\u5f00");
            radioButton_doorOpen.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_doorOpenMouseClicked(e);
                }
            });
            doorPanel.add(radioButton_doorOpen);
            radioButton_doorOpen.setBounds(new Rectangle(new Point(10, 19), radioButton_doorOpen.getPreferredSize()));

            //---- radioButton_doorClose ----
            radioButton_doorClose.setText("\u5173\u95ed");
            radioButton_doorClose.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    radioButton_doorCloseMouseClicked(e);
                }
            });
            doorPanel.add(radioButton_doorClose);
            radioButton_doorClose.setBounds(new Rectangle(new Point(10, 48), radioButton_doorClose.getPreferredSize()));

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < doorPanel.getComponentCount(); i++) {
                    Rectangle bounds = doorPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = doorPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                doorPanel.setMinimumSize(preferredSize);
                doorPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(doorPanel);
        doorPanel.setBounds(10, 355, 145, 75);

        contentPane.setPreferredSize(new Dimension(780, 465));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        // 设置窗体右上角关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //

        // 离合器panel绘制边框
        clutchPedalPanel.setBorder(BorderFactory.createTitledBorder("离合器踏板"));
        // 离合器状态按钮组
        buttonGroup_clutch = new ButtonGroup();
        buttonGroup_clutch.add(radioButton_clutchPedalOn);
        buttonGroup_clutch.add(radioButton_clutchPedalOff);
        buttonGroup_clutch.setSelected(radioButton_clutchPedalOff.getModel(), true);
        radioButton_clutchPedalOff.setEnabled(false);

        // 刹车踏板panel绘制边框
        brakePedalPanel.setBorder(BorderFactory.createTitledBorder("刹车踏板"));
        // 刹车踏板状态按钮组
        buttonGroup_brake = new ButtonGroup();
        buttonGroup_brake.add(radioButton_brakePedalOn);
        buttonGroup_brake.add(radioButton_brakePedalOff);
        buttonGroup_brake.setSelected(radioButton_brakePedalOff.getModel(), true);
        radioButton_brakePedalOff.setEnabled(false);

        // 加速踏板panel绘制边框
        acceleratorPedalPanel.setBorder(BorderFactory.createTitledBorder("加速踏板"));
        // 加速踏板状态按钮组
        buttonGroup_accelerator = new ButtonGroup();
        buttonGroup_accelerator.add(radioButton_acceleratorPedalOn);
        buttonGroup_accelerator.add(radioButton_acceleratorPedalOff);
        buttonGroup_accelerator.setSelected(radioButton_acceleratorPedalOff.getModel(), true);
        radioButton_acceleratorPedalOff.setEnabled(false);

        //
        lightPanel.setBorder(BorderFactory.createTitledBorder("灯光"));
        //左右转向灯组
        buttonGroup_turnSignal = new ButtonGroup();
        buttonGroup_turnSignal.add(radioButton_lightTurnLeftSignalOn);
        buttonGroup_turnSignal.add(radioButton_lightTurnRightSignalOn);
//        buttonGroup_turnSignal.add(radioButton_lightTurnSignalOff);
//        buttonGroup_turnSignal.setSelected(radioButton_lightTurnSignalOff.getModel(), true);
//        radioButton_lightTurnSignalOff.setEnabled(false);

        // 远近灯光、交替 按钮组
        buttonGroup_highDipped = new ButtonGroup();
        buttonGroup_highDipped.add(radioButton_lightDippedOn);
        buttonGroup_highDipped.add(radioButton_lightHighOn);
        buttonGroup_highDipped.add(radioButton_lightHighDippedOn);
        buttonGroup_highDipped.add(radioButton_lightHighDippedClose);
        buttonGroup_highDipped.setSelected(radioButton_lightHighDippedClose.getModel(), true);
        radioButton_lightHighDippedClose.setEnabled(false);

        // 手刹组
        parkBrakePanel.setBorder(BorderFactory.createTitledBorder("手刹"));
        buttonGroup_parkBrake = new ButtonGroup();
        buttonGroup_parkBrake.add(radioButton_parkBrakeOn);
        buttonGroup_parkBrake.add(radioButton_parkBrakeOff);
        buttonGroup_parkBrake.setSelected(radioButton_parkBrakeOn.getModel(), true);
        radioButton_parkBrakeOn.setEnabled(false);

        // 挡位组
        gearPanel.setBorder(BorderFactory.createTitledBorder("挡位"));
        buttonGroup_gear = new ButtonGroup();
        buttonGroup_gear.add(radioButton_gearForward);
        buttonGroup_gear.add(radioButton_gearSecond);
        buttonGroup_gear.add(radioButton_gearThird);
        buttonGroup_gear.add(radioButton_gearFourth);
        buttonGroup_gear.add(radioButton_gearFifth);
        buttonGroup_gear.add(radioButton_gearNeutral);
        buttonGroup_gear.add(radioButton_gearReverse);
        buttonGroup_gear.setSelected(radioButton_gearNeutral.getModel(), true);
        radioButton_gearNeutral.setEnabled(false);
        lastGear = 0;
        lastGearButton = radioButton_gearNeutral;

        // 安全带组
        safetyBeltPanel.setBorder(BorderFactory.createTitledBorder("安全带"));
        buttonGroup_safetyBelt = new ButtonGroup();
        buttonGroup_safetyBelt.add(radioButton_safetyBeltOn);
        buttonGroup_safetyBelt.add(radioButton_safetyBeltOff);
        buttonGroup_safetyBelt.setSelected(radioButton_safetyBeltOff.getModel(), true);
        radioButton_safetyBeltOff.setEnabled(false);

        // 车门组
        doorPanel.setBorder(BorderFactory.createTitledBorder("车门"));
        buttonGroup_door = new ButtonGroup();
        buttonGroup_door.add(radioButton_doorOpen);
        buttonGroup_door.add(radioButton_doorClose);
        buttonGroup_door.setSelected(radioButton_doorOpen.getModel(), true);
        radioButton_doorOpen.setEnabled(false);

        //速度label文字右对齐
        speedLabel.setHorizontalAlignment(JTextField.RIGHT);
        // 公里数balel文字右对齐
        mileageLabel.setHorizontalAlignment(JTextField.RIGHT);

        //方向盘
        steerWheelPanel.setBorder(BorderFactory.createTitledBorder("方向盘"));

        // other
        otherPanel.setBorder((BorderFactory.createTitledBorder("杂项")));

        car = Car.getInstance();

//        gearActionHandler.start();
        acceleratorActionHandler.start();
        brakeActionHandler.start();
        parkBrakeActionHandler.start();
        tcpResponseMessage = new TcpResponseMessage();

        closeTurnLeftSignalThread = null;
        closeTurnRightSignalThread = null;

        if (serverSocket != null) {
            messageListener = new MessageListener(serverSocket, socket, oos, ois, this);
            messageListener.start();
        }

        messageSender = new MessageSender(messageTaskQueue, oos);
        messageSender.start();

        odometer = new Odometer();
        odometer.setSpeed(0D);
        odometer.start();

        if (serverSocket != null) {
            this.addWindowListener(this);
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label3;
    private JLabel mileageLabel;
    private JLabel label2;
    private JLabel label5;
    private JPanel lightPanel;
    private JRadioButton radioButton_lightDippedOn;
    private JRadioButton radioButton_lightHighOn;
    private JRadioButton radioButton_lightHighDippedOn;
    private JRadioButton radioButton_lightTurnLeftSignalOn;
    private JRadioButton radioButton_lightTurnRightSignalOn;
    private JRadioButton radioButton_lightFogOn;
    private JRadioButton radioButton_lightOutLineMarkOn;
    private JRadioButton radioButton_lightHazardWarnOn;
    private JRadioButton radioButton_lightHighDippedClose;
    private JLabel label4;
    private JPanel gearPanel;
    private JRadioButton radioButton_gearForward;
    private JRadioButton radioButton_gearSecond;
    private JRadioButton radioButton_gearThird;
    private JRadioButton radioButton_gearFourth;
    private JRadioButton radioButton_gearFifth;
    private JRadioButton radioButton_gearReverse;
    private JRadioButton radioButton_gearNeutral;
    private JPanel pedalPanel;
    private JPanel clutchPedalPanel;
    private JRadioButton radioButton_clutchPedalOn;
    private JRadioButton radioButton_clutchPedalOff;
    private JPanel brakePedalPanel;
    private JRadioButton radioButton_brakePedalOn;
    private JRadioButton radioButton_brakePedalOff;
    private JPanel acceleratorPedalPanel;
    private JRadioButton radioButton_acceleratorPedalOn;
    private JRadioButton radioButton_acceleratorPedalOff;
    private JPanel parkBrakePanel;
    private JRadioButton radioButton_parkBrakeOn;
    private JRadioButton radioButton_parkBrakeOff;
    private JPanel otherPanel;
    private JRadioButton radioButton_obeserveReverseMirror;
    private JLabel label1;
    private JLabel speedLabel;
    private JPanel steerWheelPanel;
    private JButton button_steerWheelSlightTurnLeft;
    private JButton button_steerWheelModerateTurnLeft;
    private JButton button_steerWheelSlightTurnRight;
    private JButton button_steerWheelModerateTurnRight;
    private JPanel safetyBeltPanel;
    private JRadioButton radioButton_safetyBeltOn;
    private JRadioButton radioButton_safetyBeltOff;
    private JPanel doorPanel;
    private JRadioButton radioButton_doorOpen;
    private JRadioButton radioButton_doorClose;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private ButtonGroup buttonGroup_brake;
    private ButtonGroup buttonGroup_clutch;
    private ButtonGroup buttonGroup_accelerator;
    private ButtonGroup buttonGroup_turnSignal;
    private ButtonGroup buttonGroup_highDipped;
    private ButtonGroup buttonGroup_parkBrake;
    private ButtonGroup buttonGroup_gear;
    private ButtonGroup buttonGroup_door;
    private ButtonGroup buttonGroup_safetyBelt;
    private Integer lastGear = 0;
    private Integer nextGear = -2;
    private JRadioButton lastGearButton;
    private JRadioButton nextGearButton;


    private GearActionHandler gearActionHandler = new GearActionHandler();
    private AcceleratorActionHandler acceleratorActionHandler = new AcceleratorActionHandler();
    private BrakeActionHandler brakeActionHandler = new BrakeActionHandler();
    private ParkBrakeActionHandler parkBrakeActionHandler = new ParkBrakeActionHandler();
    private TcpResponseMessage tcpResponseMessage;
    private Odometer odometer;
    private CloseTurnLeftSignalThread closeTurnLeftSignalThread;
    private CloseTurnRightSignalThread closeTurnRightSignalThread;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    private ServerSocket serverSocket;

    private MessageListener messageListener;
    private MessageSender messageSender;


    private Car car;

    public static MessageTaskQueue messageTaskQueue = new MessageTaskQueue();

    public static void launch(ServerSocket serverSocket, Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarGui(serverSocket, socket, oos, ois).setVisible(true);
            }
        });
    }

    // 挂挡处理线程
    class GearActionHandler extends Thread {
        private Boolean busy = false;

        public Boolean isBusy() {
            return busy;
        }

        public void run() {
            while (true) {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                    busy = true;
                    // 进行判断
                    if (radioButton_clutchPedalOff.isEnabled()) { //踩了离合
                        if ((lastGear == -1 && nextGear == 1) || nextGear == -1 || nextGear == 0 || Math.abs(lastGear - nextGear) == 1) { // 可以挂挡
//                            nextGearButton.setEnabled(false);
//                            lastGearButton.setEnabled(true);
//                            buttonGroup_gear.clearSelection();

                            lastGear = nextGear;
                            lastGearButton = nextGearButton;

                            // 唤醒加速处理线程
                            if (acceleratorActionHandler.isWaittingGear()) {
                                synchronized (acceleratorActionHandler) {
                                    acceleratorActionHandler.notify();
                                }
                            }

                            buttonGroup_gear.setSelected(nextGearButton.getModel(), true);
                            switch (nextGear) {
                                case 0:
//                                    messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("挂空挡"));
                                    System.out.println("挂空挡");
                                    break;
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
//                                    messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("挂"+nextGear+"挡"));
                                    System.out.println("挂" + nextGear + "挡");
                                    break;
                                case -1:
//                                    messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("挂倒挡"));
                                    System.out.println("挂倒挡");
                                    break;
                            }
                        } else {
                            buttonGroup_gear.clearSelection();
                            nextGearButton.setSelected(false);
                            buttonGroup_gear.setSelected(lastGearButton.getModel(), true);
//                            messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("越级挂挡"));
                            System.err.println("越级挂挡");
                        }

                    } else { // 没踩离合
                        buttonGroup_gear.clearSelection();
                        nextGearButton.setSelected(false);
                        buttonGroup_gear.setSelected(lastGearButton.getModel(), true);
//                        messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("未踩离合挂挡"));
                        System.err.println("未踩离合挂挡");
                    }
                    // 结束判断
                    busy = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 加速踏板处理线程
    class AcceleratorActionHandler extends Thread {
        private Boolean busy = false;
        private Boolean waittingGear = false;
        private Boolean waittingParkBrakeOff = false;

        public Boolean isBusy() {
            return busy;
        }

        public Boolean isWaittingGear() {
            return waittingGear;
        }

        public Boolean isWaittingParkBrakeOff() {
            return waittingParkBrakeOff;
        }

        public void run() {
            Double speed = Double.valueOf(speedLabel.getText());
            while (true) {
                busy = false;
                try {
                    synchronized (this) {
                        this.wait();
                    }
                    busy = true;
//                    messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("踩加速踏板"));
                    System.out.println("踩加速踏板");
                    waittingParkBrakeOff = false;
                    waittingGear = false;
                    Integer t = 0;
                    Double oldSpeed = Double.valueOf(speedLabel.getText());
                    odometer.wait = true;
                    boolean firstMax = true;
                    while (radioButton_acceleratorPedalOn.isEnabled() == false) { //踩加速踏板状态
                        if (radioButton_gearNeutral.isEnabled() && radioButton_parkBrakeOn.isEnabled()) { // 挂了挡，没拉手刹
                            if(t >= 20){
                                if(firstMax){
                                    Map<String, String> map = new HashMap<>();
                                    map.put("SPEED", String.valueOf(speed));
                                    sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);

                                    odometer.setSpeed(speed);
                                    odometer.wait = false;
                                    firstMax = false;
                                }
                                continue;
                            }
                            speed = Double.valueOf(speedLabel.getText());
                            speed += 0.5;
                            speedLabel.setText(String.format("%.2f", speed));
                            sleep(150);
                            t++;
                        } else if (radioButton_gearNeutral.isEnabled() == false && radioButton_parkBrakeOn.isEnabled() == false) { // 空挡，拉手刹
//                                messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("空挡踩加速踏板"));
                            System.err.println("空挡 拉手刹 踩加速踏板");
                            busy = false;
                            waittingGear = true;
                            waittingParkBrakeOff = true;
                            synchronized (this) {
                                this.wait();
                            }
                        } else if (radioButton_gearNeutral.isEnabled() == false && radioButton_parkBrakeOn.isEnabled()) {// 空挡，没拉手刹
                            System.err.println("空挡 踩加速踏板");
                            busy = false;
                            waittingGear = true;
                            synchronized (this) {
                                this.wait();
                            }
                        } else if (radioButton_gearNeutral.isEnabled() && radioButton_parkBrakeOn.isEnabled() == false) { // 挂挡 拉手刹
                            System.err.println("拉手刹 踩加速踏板");
                            busy = false;
                            waittingParkBrakeOff = true;
                            synchronized (this) {
                                this.wait();
                            }
                        }
                    }
                    Double tempL = 0D;
                    tempL = oldSpeed * (t * 150D / 3600000) +
                            1D / 2 * (0.5D) * (t * 150D / 3600000) * (t * 150D / 3600000);
                    Double lastL = Double.valueOf(mileageLabel.getText());
                    mileageLabel.setText(String.format("%.2f", tempL + lastL));
                    odometer.setSpeed(speed);
                    odometer.wait = false;

//                  messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("松开加速踏板"));
                    System.out.println("松开加速踏板");
                    Map<String, String> map = new HashMap<>();
                    map.put("SPEED", String.valueOf(speed));
                    sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 刹车踏板处理线程
    class BrakeActionHandler extends Thread {
        private Boolean busy = false;

        public Boolean isBusy() {
            return busy;
        }

        public void run() {
            Double speed = Double.valueOf(speedLabel.getText());
            while (true) {
                busy = false;
                try {
                    synchronized (this) {
                        this.wait();
                    }
                    System.out.println("踩刹车踏板");
                    busy = true;
                    Integer t = 0;
                    odometer.wait = true;
                    Double oldSpeed = Double.valueOf(speedLabel.getText());
                    while (radioButton_brakePedalOn.isEnabled() == false) { // 踩刹车踏板
                        // 读速度
                        speed = Double.valueOf(speedLabel.getText());
                        if (speed > 0) {
                            speed -= 0.5;
                            t++;
                            if(speed <= 0){
                                odometer.setSpeed(0D);
                                odometer.wait = true;
                                Map<String, String> map = new HashMap<>();
                                map.put("SPEED", "0");
                                sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);
                            }
                        }
                        speedLabel.setText(String.format("%.2f", speed));
                        sleep(100);
                    }
                    System.out.println("松开刹车踏板");

                    Double tempL = 0D;
                    tempL = oldSpeed * (t * 100D / 3600000) -
                            1D / 2 * (0.5D) * (t * 100D / 3600000) * (t * 100D / 3600000);
                    Double lastL = Double.valueOf(mileageLabel.getText());
                    mileageLabel.setText(String.format("%.2f", tempL + lastL));
                    odometer.wait = false;
                    Map<String, String> map = new HashMap<>();
                    map.put("SPEED", String.valueOf(speed));
                    sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    // 手刹处理线程
    class ParkBrakeActionHandler extends Thread {
        Boolean busy = false;

        public Boolean isBusy() {
            return busy;
        }

        public void run() {
            Double speed = Double.valueOf(speedLabel.getText());
            while (true) {
                try {
                    busy = true;
                    Boolean flag = true;
                    Double oldSpeed = Double.valueOf(speedLabel.getText());
                    Integer t = 0;
                    while (radioButton_parkBrakeOn.isEnabled() == false) { // 拉手刹状态
                        // 读速度
                        speed = Double.valueOf(speedLabel.getText());
                        if (speed > 0) {
                            speed -= 0.5;
                            t++;
                            if(speed <= 0){
                                odometer.setSpeed(0D);
                                odometer.wait = true;
                                Map<String, String> map = new HashMap<>();
                                map.put("SPEED", "0");
                                sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);
                            }
                        }
                        speedLabel.setText(String.format("%.2f", speed));
                        sleep(60);
                    }
                    System.out.println("放手刹");
                    // 唤醒加速处理线程
                    if (acceleratorActionHandler.isWaittingParkBrakeOff()) {
                        synchronized (acceleratorActionHandler) {
                            acceleratorActionHandler.notify();
                        }
                    }
                    Map<String, String> map = new HashMap<>();
                    map.put("SPEED", String.valueOf(speed));
                    sendMessage(map, TcpResponseMessage.RESPONSE_BASE_STATE);
                    Double tempL = 0D;
                    tempL = oldSpeed * (t * 60D / 3600000) -
                            1D / 2 * (0.5D) * (t * 60D / 3600000) * (t * 60 / 3600000);
                    Double lastL = Double.valueOf(mileageLabel.getText());
                    mileageLabel.setText(String.format("%.2f", tempL + lastL));
                    odometer.setSpeed(speed);
                    odometer.wait = false;

                    busy = false;
                    synchronized (this) {
                        this.wait();
                    }
                    odometer.wait = true;
                    System.out.println("拉手刹");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //关闭左转灯线程
    volatile private boolean exitCloseTurnLeftSignalThread = false;
    class CloseTurnLeftSignalThread extends Thread{

        public void run() {
            try {
                int i = 5;
                while (i>0 && !exitCloseTurnLeftSignalThread){
                    label4.setText("("+(i--)+"秒后自动关闭)");
                    synchronized (this) {
                        this.wait(1000 * 1);
                    }
                }
                if(exitCloseTurnLeftSignalThread){
                   return;
                }
                radioButton_lightTurnLeftSignalOn.setEnabled(true);
                radioButton_lightTurnRightSignalOn.setEnabled(true);
                buttonGroup_turnSignal.clearSelection();
                label4.setText("");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //关闭右转灯线程
    volatile private boolean exitCloseTurnRightSignalThread = false;
    class CloseTurnRightSignalThread extends Thread{
        public void run() {
            try {
                int i = 5;
                while (i>0 && !exitCloseTurnRightSignalThread){
                    label4.setText("("+(i--)+"秒后自动关闭)");
                    synchronized (this) {
                        this.wait(1000 * 1);
                    }
                }
                if(exitCloseTurnRightSignalThread){
                    return;
                }
                radioButton_lightTurnRightSignalOn.setEnabled(true);
                radioButton_lightTurnLeftSignalOn.setEnabled(true);
                buttonGroup_turnSignal.clearSelection();
                label4.setText("");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 接收消息线程
    class MessageListener extends Thread {
        ObjectInputStream ois;
        ObjectOutputStream oos;
        Socket socket;
        ServerSocket serverSocket;
        CarGui currentFrame;

        public MessageListener(ServerSocket serverSocket, Socket socket, ObjectOutputStream oos, ObjectInputStream ois, CarGui currentFrame) {
            this.ois = ois;
            this.oos = oos;
            this.socket = socket;
            this.serverSocket = serverSocket;
            this.currentFrame = currentFrame;
        }

        public void run() {
            try {
                while (socket.isClosed() == false) {
                    TcpRequestMessage tcpRequestMessage = (TcpRequestMessage) ois.readObject();

                    if (tcpRequestMessage.getTypeName().equals(TcpRequestMessage.REQUEST_TCP_CONNECT_CLOSE)) {
                        oos.close();
                        ois.close();
                        socket.close();
                        serverSocket.close();
                        currentFrame.dispose();
                        MainGui.launch(true);
                        this.stop();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("读考试端发送的数据失败");
            }
            System.out.println("退出while循环");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentFrame.setVisible(false);
            MainGui.launch(true);
        }

    }

    class Odometer extends Thread {
        volatile public Boolean wait = false;
        Double speed;
        //里程
        private Double mileage;
        public Odometer(){
            speed = 0D;
            mileage = 0D;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }

        public void run() {
            try {
                while (true) {
                    while (!wait) {
                        sleep(1000);
                        mileage = mileage + speed * 1D / 3600;

                        mileageLabel.setText(String.format("%.2f", mileage));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(List<String> examItemOperationNameList, String typeName) {
        tcpResponseMessage = new TcpResponseMessage();
        tcpResponseMessage.setTypeName(typeName);
        tcpResponseMessage.setExamItemOperationName(examItemOperationNameList);

        messageTaskQueue.putMessage(tcpResponseMessage);
    }
    private void sendMessage(List<String> examItemOperationNameList, Map<String, String> map, String typeName) {
        tcpResponseMessage = new TcpResponseMessage();
        tcpResponseMessage.setTypeName(typeName);
        tcpResponseMessage.setDataMap(map);
        tcpResponseMessage.setExamItemOperationName(examItemOperationNameList);

        messageTaskQueue.putMessage(tcpResponseMessage);
    }
    private void sendMessage(Map<String, String> map, String typeName) {
        tcpResponseMessage = new TcpResponseMessage();
        tcpResponseMessage.setTypeName(typeName);
        tcpResponseMessage.setDataMap(map);

        messageTaskQueue.putMessage(tcpResponseMessage);
    }

    private void checkGearShift(){
        if (radioButton_clutchPedalOff.isEnabled()) { //踩了离合
            if ((lastGear == -1 && nextGear == 1) || nextGear == -1 || nextGear == 0 || Math.abs(lastGear - nextGear) == 1) { // 可以挂挡
                nextGearButton.setEnabled(false);
                lastGearButton.setEnabled(true);
                buttonGroup_gear.clearSelection();

                lastGear = nextGear;
                lastGearButton = nextGearButton;

                // 唤醒加速处理线程
                if (acceleratorActionHandler.isWaittingGear()) {
                    synchronized (acceleratorActionHandler) {
                        acceleratorActionHandler.notify();
                    }
                }

                buttonGroup_gear.setSelected(nextGearButton.getModel(), true);
                switch (nextGear) {
                    case 0:
//                                    messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("挂空挡"));
                        System.out.println("挂空挡");
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
//                                    messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("挂"+nextGear+"挡"));
                        System.out.println("挂" + nextGear + "挡");
                        break;
                    case -1:
//                                    messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("挂倒挡"));
                        System.out.println("挂倒挡");
                        break;
                }
            } else {
                buttonGroup_gear.clearSelection();
                nextGearButton.setSelected(false);
                buttonGroup_gear.setSelected(lastGearButton.getModel(), true);
//                            messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("越级挂挡"));
                System.err.println("越级挂挡");
            }

        } else { // 没踩离合
            buttonGroup_gear.clearSelection();
            nextGearButton.setSelected(false);
            buttonGroup_gear.setSelected(lastGearButton.getModel(), true);
//                        messageTaskQueue.putMessage(new SingleOperationMessageTask<String>("未踩离合挂挡"));
            System.err.println("未踩离合挂挡");
        }
        // 结束判断
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            this.messageListener.stop();
            this.messageSender.stop();
            if (this.socket != null && this.socket.isClosed() == false)
                this.socket.close();
            if (this.serverSocket != null && this.serverSocket.isClosed() == false)
                this.serverSocket.close();
            this.dispose();
            MainGui.launch(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public static void main(String[] args) {
        CarGui.launch(null, null, null, null);
    }
}
