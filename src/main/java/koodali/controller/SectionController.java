package koodali.controller;

import koodali.model.Section;
import koodali.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class SectionController {

    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /**
     *
     * */
    @GetMapping("/sections")
    public ResponseEntity<List<Section>> getAllSections() {
        return new ResponseEntity<>(sectionService.getAllSections(), HttpStatus.OK);
    }

    @GetMapping("/sections/{classId}/students")
    public ResponseEntity<List<String>> showInfoStudentsOfSection(@PathVariable String classId) {
        List<String> names = sectionService.getSectionByID(classId)
                .getStudents()
                .values()
                .stream()
                .map(
                        student -> student.getFirstName() + " " + student.getLastName()
                                + " from " + student.getCity() + " , " + student.getCountry() + " , Section :"
                                + classId

                ).toList();

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping("/sections/{classId}/students")
    public ResponseEntity<List<String>> showInfoTeachersOfSection(@PathVariable String classId) {
        List<String> names = sectionService.getSectionByID(classId)
                .getTeachers()
                .values()
                .stream()
                .map(
                        teacher -> teacher.getFirstName() + " " + teacher.getLastName()
                                + " , teaching class " + classId + " since " //TODO

                ).toList();

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

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
