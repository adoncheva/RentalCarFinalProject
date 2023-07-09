package com.example.RentalCarProject.Services.Implementation;
import com.example.RentalCarProject.Convertors.CarConverter;
import com.example.RentalCarProject.DTOs.CarRequest;
import com.example.RentalCarProject.DTOs.CarResponse;
import com.example.RentalCarProject.DTOs.CarUpdateResponse;
import com.example.RentalCarProject.Entities.Car;
import com.example.RentalCarProject.Entities.User;
import com.example.RentalCarProject.Repositories.CarRepository;
import com.example.RentalCarProject.Repositories.UserRepository;
import com.example.RentalCarProject.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarConverter carConverter;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    @Autowired
    public CarServiceImpl(CarConverter carConverter, CarRepository carRepository, UserRepository userRepository) {
        this.carConverter = carConverter;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CarResponse saveCar(CarRequest request) {

        Car car = carConverter.toCar(request);

        Car savedCar = carRepository.save(car);

        return carConverter.toCarResponse(savedCar);
    }

    @Override
    public CarResponse getCar(Long id) {
        Car car = carRepository.findById(id).orElse(new Car());
        return carConverter.toCarResponse(car);

    }
    @Override
    public void deletedCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarUpdateResponse updatedCar(Long id, CarRequest carRequest) {
        Car car = carRepository.findById(id).orElseThrow();
        if (carRequest.getBrand() != null &&!carRequest.getBrand().isBlank()) {
            car.setBrand(carRequest.getBrand());
        }
        if (carRequest.getModel() != null &&!carRequest.getModel().isBlank()) {
            car.setModel(carRequest.getModel());
        } if (carRequest.getNumberOfSeats() != null &&carRequest.getNumberOfSeats()!=0) {
            car.setNumberOfSeats(carRequest.getNumberOfSeats());
        }if (carRequest.getPricePerDay() != null && carRequest.getPricePerDay()!=0.0) {
            car.setPricePerDay(carRequest.getPricePerDay());
        }

        return carConverter.toCarUpdateResponse(carRepository.save(car));
    }
}
