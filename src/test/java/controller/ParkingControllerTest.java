package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

//import org.aspectj.lang.annotation.Before;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import Entities.Car;
import Entities.Slot;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import parkingLot.Application;
import services.ParkingService;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes= Application.class)
@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
class ParkingControllerTest {
    @Mock
    ParkingService parkingService;

    @InjectMocks
    ParkingController parkingController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this.parkingController);
        this.mockMvc = MockMvcBuilders.standaloneSetup(parkingController).build();
    }

    @Test
    public void testSlotsCreated() throws Exception
    {
        Slot slot = new Slot(1);
        ArrayList<Slot> Success = new ArrayList<Slot>();
        Success.add(slot);

        when(parkingService.createSlots(10)).thenReturn(Success);

        mockMvc.perform(post("/setSlots")
                        .param("slots","10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].slots").value("1"))
                .andExpect(jsonPath("$[0].slotFree").value("true"));
        verify(parkingService, times(1)).createSlots(10);
        verifyNoMoreInteractions(parkingService);

    }

    @Test
    public void parkACar() throws Exception
    {
        Car car = new Car("TS091234","Red");
        when(parkingService.parkACar("Blue","123")).thenReturn(car.getTokenNumber());
        mockMvc.perform(post("/parkACar")
                        .param("carNumber","123")
                        .param("carColor","Blue")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['carDetails'].carColor").value("Blue"))
                .andExpect(jsonPath("$['carDetails'].carNumber").value("123"))
                .andExpect(jsonPath("$.tokenNumber").value("123123"));
        verify(parkingService, times(1)).parkACar("123","red");
        verifyNoMoreInteractions(parkingService);

    }

    @Test
    public void searchCarByNum() throws Exception
    {
        Car car = new Car("TS091234","Red");

        when(parkingService.filterByCarNumber("123")).thenReturn(car);

        mockMvc.perform(get("/searchCar/{carNumber}", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carNumber", is("123123")))
                .andExpect(jsonPath("$['carDetails'].carNumber", is("123"))
                );

        verify(parkingService, times(1)).filterByCarNumber("123");
        verifyNoMoreInteractions(parkingService);

    }
    @Test
    public void UnParkTheCar() throws Exception
    {
        String responseString = "Car entry removed";

        when(parkingService.unParkCar(1)).thenReturn(responseString);

        mockMvc.perform(delete("/unParkCar/{slotNumber}", "123123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['carDetails'].carNumber").value("123"));

        verify(parkingService, times(1)).unParkCar(1);
        verifyNoMoreInteractions(parkingService);

    }

}
