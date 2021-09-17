package controller;
import Entities.Car;
import Entities.Slot;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ParkingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    @PostMapping("/setSlots/{slots}")
    public List<Slot> createSlots(@PathVariable int slots){
       List<Slot> totalSlots = parkingService.createSlots(slots);
       return totalSlots;
    }

//    @PostMapping("/parkACar")
//    public List<Car> parkACar(@RequestParam("carNumber") String carNumber, @RequestParam("carColor") String carColor){
//    {
//        List<Car> result = parkingService.parkACar(carNumber,carColor);
//        return result;
//    }

    @PostMapping("/parkACar")
    public Car parkACar(@RequestBody()JsonNode car) throws Exception
    {
        String carNumber = car.get(0).get("carNumber").asText();
        String carColor = car.get(0).get("carColor").asText();
        return parkingService.parkACar(carNumber,carColor);
    }

    @DeleteMapping("/unParkCar/{slotNumber}")
    public String unParkACar(@PathVariable int slotNumber)throws Exception{
        System.out.println("UnPark Car");
        return parkingService.unParkCar(slotNumber);
    }

    @GetMapping("/showParkingDetails")
    public String showParkingDetails(){
        return parkingService.showParkedCars();
    }

    @GetMapping(value = "/searchCar/{carNumber}")
    public Car search(@PathVariable("carNumber") String carNumber)throws Exception{
        Car result = parkingService.filterByCarNumber(carNumber);
        return result;
    }



}