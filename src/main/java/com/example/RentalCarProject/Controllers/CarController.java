package com.example.RentalCarProject.Controllers;

import com.example.RentalCarProject.DTOs.CarRequest;
import com.example.RentalCarProject.DTOs.CarResponse;
import com.example.RentalCarProject.DTOs.CarUpdateResponse;
import com.example.RentalCarProject.Services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/add")
    public ResponseEntity<String> addCar(@Valid @RequestBody CarRequest request){

      CarResponse carResponse = carService.saveCar(request);
      String response = String.format("Car with brand %s was created with %s id",
              carResponse.getBrand(),carResponse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(carService.getCar(id));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedCar (@PathVariable Long id){
        carService.deletedCar(id);
        String response = String.format("Car with %s id has been deleted.",id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    @PutMapping(path="/update/{id}")
    public ResponseEntity<CarUpdateResponse> updatedCar(@PathVariable Long id, @RequestBody CarRequest carRequest ){

        CarUpdateResponse carResponse=carService.updatedCar(id,carRequest);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }
}
