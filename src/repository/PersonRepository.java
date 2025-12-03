package repository;

import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class PersonRepository<T extends Person> {
    protected final HashMap<String, T> persons = new HashMap<>();


    public boolean containsPerson(T Person){
        return persons.containsValue(Person);
    }

    public boolean containsPerson(String PersonID){
        return persons.containsKey(PersonID);
    }


    public Optional<Person> findByPersonID(String personID) {
        Optional<String> first = persons.keySet().stream().filter(s -> s.equals(personID)).findFirst();
        return first.map(persons::get);
    }

    public T deletePerson(String personID){
        return persons
                .remove(personID);
    }

    public T addPerson(T person){
        return persons
                .put(person.getID(),person);
    }

    public List<T> getAll(){
        return persons.values().stream().toList();
    }
}
