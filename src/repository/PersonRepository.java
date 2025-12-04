package repository;

import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class PersonRepository<T extends Person> {
    protected final HashMap<String, T> persons = new HashMap<>();


    public boolean contains(T Person){
        return persons.containsValue(Person);
    }

    public boolean contains(String PersonID){
        return persons.containsKey(PersonID);
    }


    public Optional<Person> findByID(String personID) {
        Optional<String> first = persons.keySet().stream().filter(s -> s.equals(personID)).findFirst();
        return first.map(persons::get);
    }

    public T delete(String personID){
        return persons
                .remove(personID);
    }

    public T add(T person){
        return persons
                .put(person.getID(),person);
    }

    public List<T> getAll(){
        return persons.values().stream().toList();
    }

    public T update(T person){
        return persons
                .put(person.getID(),person);
    }
}
