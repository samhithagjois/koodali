package controller;

import model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SectionService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/sections")
    public ResponseEntity<List<Section>> getAllSections() {
        return new ResponseEntity<>(sectionService.getAllSections(), HttpStatus.OK);
    }


    //TODO!

    //getAllSections
    //updateSection
    //deleteSection
    //addNewSection




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
