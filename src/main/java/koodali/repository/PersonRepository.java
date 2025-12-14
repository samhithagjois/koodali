package koodali.repository;

import koodali.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class PersonRepository<T extends Person> {
    protected final HashMap<String, T> persons = new HashMap<>();


    public boolean contains(T Person) {
        return persons.containsValue(Person);
    }

    public boolean contains(String PersonID) {
        return persons.containsKey(PersonID);
    }


    /**
     * finds the person involved by their id. Preferable use this in backend
     * @param personID the ID of the person
     * @return an Optional<Person> because we need to know if they exist or not!
     * */
    public Optional<T> findByID(String personID) {
        Optional<String> first = persons.keySet().stream().filter(s -> s.equals(personID)).findFirst();
        return first.map(persons::get);
    }

    /**
     * finds the person by their name. Not case sensitive, will be used in frontend much more
     * @param name , can be any part of the persons name
     * @return an Optional Person
     * */
    public Optional<T> findByName(String name) {
        return persons
                .values()
                .stream()
                .filter(t -> t.getFirstName().equalsIgnoreCase(name) || t.getLastName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * deletes the Person from the System entirely, so be careful
     * */
    public T delete(String personID) {
        return persons
                .remove(personID);
    }

    /**
     * adds Person to the system
     * */
    public T add(T person) {
        return persons
                .put(person.getID(), person);
    }

    public List<T> getAll() {
        return persons.values().stream().toList();
    }

    public T update(T person) {
        return persons
                .put(person.getID(), person);
    }
}
