package koodali.service;

import koodali.model.Person;
import koodali.repository.PersonRepository;

import java.util.List;

public abstract class PersonService<T extends Person> {

    private final PersonRepository<T> personRepo;

    protected PersonService(PersonRepository<T> personRepo) {
        this.personRepo = personRepo;
    }

    public List<T> getAll(){
       return personRepo.getAll();
    }

    public T update(T person){
        return personRepo.update(person);
    }

    public T add(T person){
        return personRepo.add(person);
    }

    public T findByID(String id){
        if(personRepo.findByID(id).isPresent()){
            return personRepo.findByID(id).get();
        }else{
            return null;
        }
    }

    public T delete(String id){
        return personRepo.delete(id);
    }

}
