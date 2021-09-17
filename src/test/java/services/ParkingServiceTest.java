package services;

import Entities.Car;
import Entities.Slot;
import controller.ParkingController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ParkingServiceTest {

    @Mock
    ParkingService parkingService;

    @InjectMocks
    ParkingController parkingController;

    private MockMvc mockMvc;

    @Test
    public void testSlotsCreated() throws Exception
    {
        Slot slot = new Slot(1);
        ArrayList<Slot> Success = new ArrayList<Slot>();
        Success.add(slot);


    }



    @Test
    void parkACar() {
    }
}