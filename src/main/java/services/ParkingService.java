package services;

import Entities.Car;
import Entities.Response;
import Entities.Slot;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ParkingService {
    ArrayList<Slot> availableSlotList;
    List<Car> parkedCarList = new ArrayList<Car>();

    public ArrayList<Slot> createSlots(int slots){
        ArrayList<Slot> slotList = new ArrayList<Slot>();
        for(int slotNumber=1;slotNumber<=slots;slotNumber++){
            slotList.add(new Slot(slotNumber));
        }
        return this.availableSlotList=slotList;
    }

    public Car parkACar(String carNumber, String carColor) throws Exception{
        Car car = new Car(carNumber,carColor);
        Response response = new Response();
        if(isSlotAvailable()){
            Slot availableSlot = getTheNextFreeSlot();
            car.setSlotNumber(availableSlot.getSlotNumber());
            car.setTokenNumber(UUID.randomUUID().toString());
            parkedCarList.add(car);
            return car;
        }else {
            throw new Exception("No slots available");
        }
    }

    private boolean isSlotAvailable() {
        boolean isSlotAvailable = false;
        for(Slot slot:availableSlotList){
            if(slot.isSlotFree()){
                isSlotAvailable = true;
                break;
            }
        }
        return isSlotAvailable;
    }

    private Slot getTheNextFreeSlot() throws Exception{
        for(Slot slot : availableSlotList){
            if(slot.isSlotFree()){
                slot.makeSlotOccupied();
                return slot;
            }
        }
        throw new Exception("Slot is not created");
    }

    public String showParkedCars(){
        for(Car cars:parkedCarList){
            System.out.println("Cars in parking");
            return "\nCar Number: " +cars.getCarNumber()
                    +"\nSlot Number: " +cars.getSlotNumber()
                    +"\nCar Color: " +cars.getCarColor()
                    +"\nToken Number: " +cars.getTokenNumber();

        }
        return "No any car so far";
    }


    public Car filterByCarNumber(String carNumber)throws Exception {
            //carList = query records from car object based on car number field
            for(Car car:parkedCarList){
                if(car.getCarNumber().equals(carNumber)){
//                    System.out.println("Slot number: " + car.getSlotNumber());
//                    System.out.println("car color: " + car.getCarNumber());
//                    System.out.println("car color: " + car.getCarColor());
//                    System.out.println("car color: " + car.getTokenNumber());
                    return  car;
                }
            }
        throw new Exception("No car available with provided car Number");
    }

    public String  unParkCar(int slotNumber)throws Exception{
        for (Slot slot:availableSlotList){
            if(slot.getSlotNumber() == slotNumber){
                slot.makeSlotFree();
                return "Car entry removed";
            }
        }
        throw new Exception("Provide valid slotNumber");
    }


}
