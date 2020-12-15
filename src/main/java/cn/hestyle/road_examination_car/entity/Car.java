package cn.hestyle.road_examination_car.entity;

import cn.hestyle.road_examination_car.util.HightDipped;
import cn.hestyle.road_examination_car.util.TurnSignal;

public class Car {
    private Boolean stepClutchOn;
    private Boolean stepBrakeOn;
    private Boolean accreleratorStepOn;
    private TurnSignal lightTurnSignal;
    private HightDipped lightHighDipped;
    private Boolean lightFogOn;
    private Boolean lightOutLineMarkOn;
    private Boolean lightHazardWarnOn;
    private Integer gear;
    private Boolean safetyBeltOn;
    private Boolean doorOpen;
    private Boolean parkBrakeOn;

    private Double speed;


    private volatile static Car car;
    private Car(){
        stepBrakeOn = false;
        stepBrakeOn = false;
        accreleratorStepOn = false;

        lightTurnSignal = TurnSignal.OFF;
        lightHighDipped = HightDipped.OFF;
        lightFogOn = false;
        lightHazardWarnOn = false;
        lightOutLineMarkOn = false;

        gear = 0;

        doorOpen = true;

        safetyBeltOn = false;

        parkBrakeOn = true;

        speed = 0D;
    }

    public static Car getInstance(){
        if(car == null){
            synchronized (Car.class){
                if(car == null){
                    car = new Car();
                }
            }
        }
        return car;
    }

    public Boolean getStepClutchOn() {
        return stepClutchOn;
    }

    public void setStepClutchOn(Boolean stepClutchOn) {
        this.stepClutchOn = stepClutchOn;
    }

    public Boolean getStepBrakeOn() {
        return stepBrakeOn;
    }

    public void setStepBrakeOn(Boolean stepBrakeOn) {
        this.stepBrakeOn = stepBrakeOn;
    }

    public Boolean getAccreleratorStepOn() {
        return accreleratorStepOn;
    }

    public void setAccreleratorStepOn(Boolean accreleratorStepOn) {
        this.accreleratorStepOn = accreleratorStepOn;
    }

    public TurnSignal getLightTurnSignal() {
        return lightTurnSignal;
    }

    public void setLightTurnSignal(TurnSignal lightTurnSignal) {
        this.lightTurnSignal = lightTurnSignal;
    }

    public HightDipped getLightHighDipped() {
        return lightHighDipped;
    }

    public void setLightHighDipped(HightDipped lightHighDipped) {
        this.lightHighDipped = lightHighDipped;
    }

    public Boolean getLightFogOn() {
        return lightFogOn;
    }

    public void setLightFogOn(Boolean lightFogOn) {
        this.lightFogOn = lightFogOn;
    }

    public Boolean getLightOutLineMarkOn() {
        return lightOutLineMarkOn;
    }

    public void setLightOutLineMarkOn(Boolean lightOutLineMarkOn) {
        this.lightOutLineMarkOn = lightOutLineMarkOn;
    }

    public Boolean getLightHazardWarnOn() {
        return lightHazardWarnOn;
    }

    public void setLightHazardWarnOn(Boolean lightHazardWarnOn) {
        this.lightHazardWarnOn = lightHazardWarnOn;
    }

    public Integer getGear() {
        return gear;
    }

    public void setGear(Integer gear) {
        this.gear = gear;
    }

    public Boolean getSafetyBeltOn() {
        return safetyBeltOn;
    }

    public void setSafetyBeltOn(Boolean safetyBeltOn) {
        this.safetyBeltOn = safetyBeltOn;
    }

    public Boolean getDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(Boolean doorOpen) {
        this.doorOpen = doorOpen;
    }

    public Boolean getParkBrakeOn() {
        return parkBrakeOn;
    }

    public void setParkBrakeOn(Boolean parkBrakeOn) {
        this.parkBrakeOn = parkBrakeOn;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "stepClutchOn=" + stepClutchOn +
                ", stepBrakeOn=" + stepBrakeOn +
                ", accreleratorStepOn=" + accreleratorStepOn +
                ", lightTurnSignal=" + lightTurnSignal +
                ", lightHighDipped=" + lightHighDipped +
                ", lightFogOn=" + lightFogOn +
                ", lightOutLineMarkOn=" + lightOutLineMarkOn +
                ", lightHazardWarnOn=" + lightHazardWarnOn +
                ", gear=" + gear +
                ", safetyBeltOn=" + safetyBeltOn +
                ", doorOpen=" + doorOpen +
                ", parkBrakeOn=" + parkBrakeOn +
                ", speed=" + speed +
                '}';
    }
}
