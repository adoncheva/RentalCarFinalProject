package com.example.RentalCarProject.Services;
import com.example.RentalCarProject.DTOs.CarRequest;
import com.example.RentalCarProject.DTOs.CarResponse;
import com.example.RentalCarProject.DTOs.CarUpdateResponse;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

    CarResponse saveCar(CarRequest request);

    CarResponse getCar(Long id);

    void deletedCar(Long id);

    CarUpdateResponse updatedCar(Long id, CarRequest carRequest);
}
