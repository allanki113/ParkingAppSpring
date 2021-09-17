package Entities;

public class Slot {
    private int slotNumber;
    private boolean isParked;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.isParked=false;
    }

    public Slot(){

    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public boolean isSlotFree() {
        return !isParked;
    }

    public Slot makeSlotFree(){
        isParked = false;
        return this;
    }

    public Slot makeSlotOccupied() {
        this.isParked = true;
        return this;
    }


}
