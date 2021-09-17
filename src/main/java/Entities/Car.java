package Entities;

public class Car {
    private String carNumber;
    private String carColor;
    private String tokenNumber;
    private int slotNumber;
    private boolean isCarParked;

    public Car(String carNumber, String carColor) {
        this.carNumber = carNumber;
        this.carColor = carColor;
    }

    public Car(){

    }

    public String getCarNumber(){
        return this.carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarColor(){
        return this.carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isCarParked() {
        return isCarParked;
    }

    public void setCarParked(boolean carParked) {
        isCarParked = carParked;
    }

}
