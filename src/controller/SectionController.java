package controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SectionController {
    /*	@GetMapping("/cars")
	public ResponseEntity<List<Car>> getAllCars() {
		return new ResponseEntity<>(ModelStorage.getAllCars(), HttpStatus.OK);
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable String id) {
		Car car = ModelStorage.getCarById(id);

		if (car != null) {
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			throw new CarNotFoundException();
		}
	}

	@PostMapping("/cars")
	public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
		newCar.setId(ModelStorage.createRandomId());
		ModelStorage.saveCar(newCar);

		return new ResponseEntity<>(newCar, HttpStatus.CREATED);
	}

	@PutMapping("/cars")
	public ResponseEntity<Car> updateCar(@RequestBody Car updatedCar) {
		Car oldCar = ModelStorage.getCarById(updatedCar.getId());

		oldCar.setRentalCostPerDay(updatedCar.getRentalCostPerDay());
		oldCar.setColor(updatedCar.getColor());
		oldCar.setBrand(updatedCar.getBrand());

		return new ResponseEntity<>(oldCar, HttpStatus.OK);
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<Car> deleteCar(@PathVariable String id) {
		Car car = ModelStorage.getCarById(id);
		ModelStorage.deleteCar(car);

		return new ResponseEntity<>(car, HttpStatus.OK);
	}*/
}
